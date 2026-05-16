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
import com.example.umc10th.global.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public MemberResDTO.SignupResultDTO signup(MemberReqDTO.SignupDTO request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new ProjectException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Image defaultImage = imageRepository.findById(1L)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.DEFAULT_IMAGE_NOT_FOUND));

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = MemberConverter.toMember(request, defaultImage, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignupResultDTO(savedMember);
    }

    @Override
    public MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request, HttpServletRequest httpServletRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Member member = userDetails.getMember();

            return MemberConverter.toLoginResultDTO(member);

        } catch (AuthenticationException e) {
            throw new ProjectException(MemberErrorCode.LOGIN_FAILED);
        }
    }
}
