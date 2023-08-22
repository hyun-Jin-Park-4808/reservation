package com.zerobase.reservation.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerReviewDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private int restaurantId;
        private int customerId;
        private String starRating;
        private String review;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int customerId;
        private int restaurantId;
        private String restaurantName;
        private String starRating;
        private String review;

        public static CustomerReviewDto.Response from(CustomerDto customerDto) {
            return Response.builder()
                    .customerId(customerDto.getCustomerId())
                    .restaurantId(customerDto.getRestaurantId())
                    .restaurantName(customerDto.getRestaurantName())
                    .starRating(customerDto.getStarRating())
                    .review(customerDto.getReview())
                    .build();
        }
    }
}
