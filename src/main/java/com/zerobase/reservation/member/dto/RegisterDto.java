package com.zerobase.reservation.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RegisterDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String userId; // email
        private String userName;
        private String phone;
        private String password;
        private boolean adminYN; // 점장 or 손님
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String userId; // email
        private String userName;
        private String phone;
        private String password;
        private LocalDateTime regDt; // 회원가입 등록일
        private boolean adminYN; // 점장 or 손님

        public static Response from(MemberDto memberDto) {
            return Response.builder()
                    .userId(memberDto.getUserId())
                    .userName(memberDto.getUserName())
                    .phone(memberDto.getPhone())
                    .adminYN(memberDto.isAdminYN())
                    .build();
        }
    }
}
