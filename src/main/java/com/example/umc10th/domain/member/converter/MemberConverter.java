package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.common.entity.Image;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;

import java.time.LocalDateTime;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignupDTO request, Image image, String encodedPassword) {
        return Member.builder()
                .name(request.getName())
                .password(encodedPassword)
                .email(request.getEmail())
                .gender(request.getGender())
                .birth(request.getBirth())
                .phoneNumber(request.getPhoneNumber())
                .nickname(request.getName())
                .socialType(SocialType.LOCAL)
                .address(request.getAddress())
                .point(0)
                .image(image)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static MemberResDTO.SignupResultDTO toSignupResultDTO(Member member) {
        return MemberResDTO.SignupResultDTO.builder()
                .userId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.LoginResultDTO toLoginResultDTO(Member member, String accessToken) {
        return MemberResDTO.LoginResultDTO.builder()
                .userId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .accessToken(accessToken)
                .build();
    }

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .profileImageUrl(member.getImage() != null ? member.getImage().getImageUrl() : null)
                .build();
    }
}
