package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResultDTO> signup(
            @RequestBody MemberReqDTO.SignupDTO request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                memberService.signup(request)
        );
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody MemberReqDTO.LoginDTO request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.login(request)
        );
    }
}
