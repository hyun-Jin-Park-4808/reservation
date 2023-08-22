package com.zerobase.reservation.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class CustomerListDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private int restaurantId; // 식당 Id
        private LocalDate date; // 조회할 날짜
    }
}
