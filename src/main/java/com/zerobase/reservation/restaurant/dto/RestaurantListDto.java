package com.zerobase.reservation.restaurant.dto;

import com.zerobase.reservation.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RestaurantListDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String locationX; // 이용자 X좌표
        private String locationY; // 이용자 Y좌표
    }

}
