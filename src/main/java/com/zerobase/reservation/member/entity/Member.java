package com.zerobase.reservation.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private String userId; // email

    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt; // 회원가입 등록일
    private boolean adminYN; // 점장 or 손님

}
