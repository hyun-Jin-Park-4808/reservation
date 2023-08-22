package com.zerobase.reservation.restaurant.repository;

import com.zerobase.reservation.restaurant.entity.Restaurant;
import com.zerobase.reservation.restaurant.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository <Review, Integer> {

    Integer countByRestaurant(Restaurant restaurant); // 레스토랑마다 리뷰 개수 카운팅

}
