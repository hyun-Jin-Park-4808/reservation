package com.zerobase.reservation.restaurant.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {
    private String restaurantName;
    private String locationX; // 상점 X좌표
    private String locationY; // 상점 Y좌표
    private String detail; // 상점 설명
    private String starRatings; // 별점
    private String distance; // 거리
}
