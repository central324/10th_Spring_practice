package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.AddressType;
import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;

public class MemberReqDTO {

    @Getter
    public static class SignupDTO {
        private String name;
        private String password;
        private String email;
        private Gender gender;
        private LocalDate birth;
        private String phoneNumber;
        private AddressType address;
    }

    @Getter
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
