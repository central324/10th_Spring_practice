package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByMemberIdAndStoreId(Long memberId, Long storeId);

    // ID 순 첫 페이지
    List<Review> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID 순 다음 페이지
    List<Review> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursorId, Pageable pageable);

    // 별점 순 첫 페이지
    @Query("""
        select r from Review r
        where r.member.id = :memberId
        order by r.rate desc, r.id desc
    """)
    List<Review> findByMemberIdOrderByRateDescIdDesc(Long memberId, Pageable pageable);

    // 별점 순 다음 페이지
    @Query("""
        select r from Review r
        where r.member.id = :memberId
          and (
                r.rate < :cursorScore
                or (r.rate = :cursorScore and r.id < :cursorId)
          )
        order by r.rate desc, r.id desc
    """)
    List<Review> findByMemberIdWithScoreCursor(
            Long memberId,
            Float cursorScore,
            Long cursorId,
            Pageable pageable
    );
}
