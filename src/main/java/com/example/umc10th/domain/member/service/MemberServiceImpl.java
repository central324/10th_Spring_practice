package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.common.entity.Image;
import com.example.umc10th.domain.common.repository.ImageRepository;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    @Override
    public MemberResDTO.SignupResultDTO signup(MemberReqDTO.SignupDTO request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new ProjectException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Image defaultImage = imageRepository.findById(1L)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.DEFAULT_IMAGE_NOT_FOUND));

        Member member = MemberConverter.toMember(request, defaultImage);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignupResultDTO(savedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new ProjectException(MemberErrorCode.INVALID_PASSWORD);
        }

        return MemberConverter.toLoginResultDTO(member);
    }
}
