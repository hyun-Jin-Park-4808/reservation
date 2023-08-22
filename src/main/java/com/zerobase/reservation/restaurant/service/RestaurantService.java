package com.zerobase.reservation.restaurant.service;

import com.zerobase.reservation.restaurant.dto.RestaurantDto;
import com.zerobase.reservation.restaurant.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    /**
     * 식당 정보 등록
     */
    RestaurantDto register(String userId, String restaurantName, String locationX, String locationY, String detail);

    /**
     * 식당 상세 정보 조회
     */
    RestaurantDto detail(int id, String restaurantName);

    /**
     * 사전 순으로 식당 조회
     */
    List<Restaurant> getRestaurantsOrderByDictionary();

    /**
     * 별점 순으로 식당 조회
     */
    List<Restaurant> getRestaurantsOrderByStarRatings();

    /**
     * 거리 순으로 식당 조회
     */
    List<Restaurant> getRestaurantsOrderByDistance(String locationX, String locationY);


}
