package com.zerobase.reservation.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RestaurantRegisterDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String userId; // email
        private String restaurantName;
        private String locationX; // 상점 X좌표
        private String locationY; // 상점 Y좌표
        private String detail; // 상점 설명
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String userId; // email
        private String restaurantName;
        private String  locationX; // 상점 X좌표
        private String locationY; // 상점 Y좌표
        private String detail; // 상점 설명

        public static RestaurantRegisterDto.Response from(RestaurantDto restaurantDto) {
            return Response.builder()
                    .userId(restaurantDto.getUserId())
                    .restaurantName(restaurantDto.getRestaurantName())
                    .locationX(restaurantDto.getLocationX())
                    .locationY(restaurantDto.getLocationY())
                    .detail(restaurantDto.getDetail())
                    .build();
        }
    }
}
