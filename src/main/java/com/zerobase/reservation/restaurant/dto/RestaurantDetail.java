package com.zerobase.reservation.restaurant.dto;

import com.zerobase.reservation.restaurant.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetail {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private int restaurantId;
        private String restaurantName;
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
        private String starRatings;
        private List<Review> reviews = new ArrayList<>();

        public static RestaurantDetail.Response from(RestaurantDto restaurantDto) {
            return Response.builder()
                    .userId(restaurantDto.getUserId())
                    .restaurantName(restaurantDto.getRestaurantName())
                    .locationX(restaurantDto.getLocationX())
                    .locationY(restaurantDto.getLocationY())
                    .detail(restaurantDto.getDetail())
                    .starRatings(restaurantDto.getStarRatings())
                    .reviews(restaurantDto.getReviews())
                    .build();
        }
    }
}
