package com.zerobase.reservation.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CustomerReservationApproval {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private boolean reservationYn;
        private int customerId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int customerId;
        private boolean reservationYn;
        private String restaurantName;

        public static CustomerReservationApproval.Response from(CustomerDto customerDto) {
            return Response.builder()
                    .customerId(customerDto.getCustomerId())
                    .restaurantName(customerDto.getRestaurantName())
                    .reservationYn(customerDto.isReservationYn())
                    .build();
        }
    }
}
