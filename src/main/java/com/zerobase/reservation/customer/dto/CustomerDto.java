package com.zerobase.reservation.customer.dto;

import com.zerobase.reservation.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private int customerId;
    private int restaurantId;
    private String restaurantName;
    private String phone;
    private LocalDate reservationDate; // 예약 날짜
    private LocalTime reservationTime; // 예약 시간
    private boolean visitedYn; // 10분 전 방문 체크
    private boolean reservationYn; // 예약 승인/거절
    private String starRating; // 사용 후 별점 주기
    private String review; // 사용 후 리뷰


    public static CustomerDto fromEntity(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .restaurantId(customer.getRestaurantId())
                .restaurantName(customer.getRestaurantName())
                .phone(customer.getPhone())
                .reservationDate(customer.getReservationDate())
                .reservationTime(customer.getReservationTime())
                .visitedYn(customer.isVisitedYn())
                .reservationYn(customer.isReservationYn())
                .starRating(customer.getStarRating())
                .review(customer.getReview())
                .build();
    }
}
