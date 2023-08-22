package com.zerobase.reservation.restaurant.repository;

import com.zerobase.reservation.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository <Restaurant, Integer> {
    List<Restaurant> findAllByOrderByRestaurantNameAsc(); // 오름차순 정렬: Asc

    List<Restaurant> findAllByOrderByStarRatingsAsc(); // 별점 순 정렬

    Restaurant findByIdAndRestaurantName(int restaurantId, String restaurantName); // 식당 상세 정보 조회

    Restaurant findById(int restaurantId);

}
