package com.example.umc10th.domain.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OAuthController {

    @GetMapping("/login/success")
    public Map<String, String> loginSuccess(@RequestParam String token) {
        return Map.of(
                "message", "카카오 로그인 성공",
                "accessToken", token
        );
    }
}
