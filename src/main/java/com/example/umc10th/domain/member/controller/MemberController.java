package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResultDTO> signup(
            @Valid @RequestBody MemberReqDTO.SignupDTO request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.SIGNUP_SUCCESS,
                memberService.signup(request)
        );
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @Valid @RequestBody MemberReqDTO.LoginDTO request,
            HttpServletRequest httpServletRequest
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.LOGIN_SUCCESS,
                memberService.login(request, httpServletRequest)
        );
    }
}
