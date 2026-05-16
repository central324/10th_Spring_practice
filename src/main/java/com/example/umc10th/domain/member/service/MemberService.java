package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {

    MemberResDTO.SignupResultDTO signup(MemberReqDTO.SignupDTO request);

    MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request, HttpServletRequest httpServletRequest);
}
