package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewResDTO.CreateReviewResultDTO createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReviewDTO request) {
        validateMemberId(memberId);
        validateStoreId(storeId);
        validateReviewRequest(request);

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ProjectException(ReviewErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(ReviewErrorCode.MEMBER_NOT_FOUND));

        if (reviewRepository.existsByMemberIdAndStoreId(member.getId(), store.getId())) {
            throw new ProjectException(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review review = Review.builder()
                .member(member)
                .store(store)
                .rate(request.getScore())
                .review_contents(request.getBody())
                .createdAt(LocalDateTime.now())
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(savedReview);
    }

    @Override
    public ReviewResDTO.MyReviewCursorPageDTO getMyReviews(
            Long memberId,
            ReviewSortType sort,
            Long cursorId,
            Float cursorScore,
            Integer size
    ) {
        validateMemberId(memberId);
        validateSize(size);

        List<Review> reviews;

        if (sort == ReviewSortType.SCORE) {
            reviews = getScoreSortedReviews(memberId, cursorId, cursorScore, size);
        } else {
            reviews = getIdSortedReviews(memberId, cursorId, size);
        }

        boolean hasNext = reviews.size() > size;

        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        Long nextCursorId = null;
        Float nextCursorScore = null;

        if (!reviews.isEmpty()) {
            Review lastReview = reviews.get(reviews.size() - 1);
            nextCursorId = lastReview.getId();

            if (sort == ReviewSortType.SCORE) {
                nextCursorScore = lastReview.getRate();
            }
        }

        return ReviewConverter.toMyReviewCursorPageDTO(reviews, hasNext, nextCursorId, nextCursorScore);
    }

    private List<Review> getIdSortedReviews(Long memberId, Long cursorId, Integer size) {
        PageRequest pageable = PageRequest.of(0, size + 1);

        if (cursorId == null) {
            return reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
        }

        return reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursorId, pageable);
    }

    private List<Review> getScoreSortedReviews(Long memberId, Long cursorId, Float cursorScore, Integer size) {
        PageRequest pageable = PageRequest.of(0, size + 1);

        if (cursorScore == null && cursorId == null) {
            return reviewRepository.findByMemberIdOrderByRateDescIdDesc(memberId, pageable);
        }

        if (cursorScore == null || cursorId == null) {
            throw new ProjectException(ReviewErrorCode.INVALID_CURSOR_REQUEST);
        }

        return reviewRepository.findByMemberIdWithScoreCursor(memberId, cursorScore, cursorId, pageable);
    }

    private void validateMemberId(Long memberId) {
        if (memberId == null || memberId <= 0) {
            throw new ProjectException(ReviewErrorCode.MEMBER_NOT_FOUND);
        }
    }

    private void validateSize(Integer size) {
        if (size == null || size <= 0) {
            throw new ProjectException(ReviewErrorCode.INVALID_CURSOR_REQUEST);
        }
    }

    private void validateStoreId(Long storeId) {
        if (storeId == null || storeId <= 0) {
            throw new ProjectException(ReviewErrorCode.STORE_NOT_FOUND);
        }
    }

    private void validateReviewRequest(ReviewReqDTO.CreateReviewDTO request) {
        if (request == null) {
            throw new ProjectException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        if (request.getScore() == null || request.getScore() < 1 || request.getScore() > 5) {
            throw new ProjectException(ReviewErrorCode.INVALID_REVIEW_SCORE);
        }

        if (request.getBody() == null || request.getBody().trim().isEmpty()) {
            throw new ProjectException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }
    }
}
