package com.zerobase.reservation.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerVisitedCheck {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private int restaurantId;
        private String restaurantName;
        private String phone;
        private int customerId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int restaurantId;
        private String restaurantName;
        private String phone;
        private int customerId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate reservationDate;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm", timezone = "Asia/Seoul")
        private LocalTime reservationTime;

        private boolean visitedYn;

        public static CustomerVisitedCheck.Response from(CustomerDto customerDto) {
            return Response.builder()
                    .customerId(customerDto.getCustomerId())
                    .restaurantId(customerDto.getRestaurantId())
                    .restaurantName(customerDto.getRestaurantName())
                    .phone(customerDto.getPhone())
                    .customerId(customerDto.getCustomerId())
                    .reservationDate(customerDto.getReservationDate())
                    .reservationTime(customerDto.getReservationTime())
                    .visitedYn(customerDto.isVisitedYn())
                    .build();
        }
    }
}
