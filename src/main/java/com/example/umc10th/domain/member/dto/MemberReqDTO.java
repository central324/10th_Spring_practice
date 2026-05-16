package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.AddressType;
import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MemberReqDTO {

    @Getter
    public static class SignupDTO {

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생년월일은 필수입니다.")
        private LocalDate birth;

        @NotBlank(message = "전화번호는 필수입니다.")
        private String phoneNumber;

        @NotNull(message = "주소는 필수입니다.")
        private AddressType address;
    }

    @Getter
    public static class LoginDTO {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }
}
