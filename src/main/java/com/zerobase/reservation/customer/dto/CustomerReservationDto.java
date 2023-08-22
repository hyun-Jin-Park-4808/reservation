package com.zerobase.reservation.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zerobase.reservation.restaurant.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerReservationDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private int restaurantId;
        private String restaurantName;
        private LocalDate reservationDate;
        private LocalTime reservationTime;
        private String phone;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int restaurantId;
        private String restaurantName;
        private String phone;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate reservationDate;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm", timezone = "Asia/Seoul")
        private LocalTime reservationTime;

        public static CustomerReservationDto.Response from(CustomerDto customerDto) {
            return Response.builder()
                    .restaurantId(customerDto.getRestaurantId())
                    .restaurantName(customerDto.getRestaurantName())
                    .phone(customerDto.getPhone())
                    .reservationDate(customerDto.getReservationDate())
                    .reservationTime(customerDto.getReservationTime())
                    .build();
        }
    }
}
