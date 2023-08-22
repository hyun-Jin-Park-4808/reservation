package com.zerobase.reservation.restaurant.dto;

import com.zerobase.reservation.restaurant.entity.Restaurant;
import com.zerobase.reservation.restaurant.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDto {
    private int restaurantId;
    private String userId; // email
    private String restaurantName;
    private String locationX; // 상점 X좌표
    private String locationY; // 상점 Y좌표
    private String detail; // 상점 설명
    private String starRatings; // 별점 평균
    private List<Review> reviews = new ArrayList<>();

    public static RestaurantDto fromEntity(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantId(restaurant.getId())
                .userId(restaurant.getUserId())
                .restaurantName(restaurant.getRestaurantName())
                .locationX(restaurant.getLocationX())
                .locationY(restaurant.getLocationY())
                .detail(restaurant.getDetail())
                .starRatings(restaurant.getStarRatings())
                .reviews(restaurant.getReviews())
                .build();
    }
}
