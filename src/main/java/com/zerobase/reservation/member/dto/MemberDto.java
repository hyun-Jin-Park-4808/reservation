package com.zerobase.reservation.member.dto;

import com.zerobase.reservation.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
        private String userId; // email
        private String userName;
        private String phone;
        private String password;
        private LocalDateTime regDt; // 회원가입 등록일
        private boolean adminYN; // 점장 or 손님

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                .password(member.getPassword())
                .regDt(member.getRegDt())
                .adminYN(member.isAdminYN())
                .build();
    }
}
