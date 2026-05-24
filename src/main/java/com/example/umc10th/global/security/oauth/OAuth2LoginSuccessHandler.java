package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String socialId = oAuth2User.getSocialId();
        String email = oAuth2User.getEmail();
        String nickname = oAuth2User.getNickname();

        Member member = memberRepository.findBySocialTypeAndSocialId(SocialType.KAKAO, socialId)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .email(email != null ? email : socialId + "@kakao.com")
                                .password(null)
                                .nickname(nickname != null ? nickname : "kakaoUser")
                                .name(null)
                                .phoneNumber(null)
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .socialType(SocialType.KAKAO)
                                .socialId(socialId)
                                .gender(null)
                                .birth(null)
                                .address(null)
                                .point(0)
                                .image(null)
                                .build()
                ));

        String accessToken = jwtUtil.createAccessToken(member.getEmail());

        response.sendRedirect("http://localhost:8080/login/success?token=" + accessToken);
    }
}
