package com.zerobase.reservation.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // id 자동 생성
    private int customerId;

    private String restaurantName;
    private int restaurantId;
    private String phone;
    private LocalDate reservationDate; // 예약 날짜
    private LocalTime reservationTime; // 예약 시간
    private boolean visitedYn; // 10분 전 방문 체크
    private boolean reservationYn; // 예약 승인/거절
    private String starRating; // 사용 후 별점 주기
    private String review; // 사용 후 리뷰

}
