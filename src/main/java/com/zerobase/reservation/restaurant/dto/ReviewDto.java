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
public class ReviewDto {
    private Restaurant restaurant;

    private String starRating;
    private String review;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .starRating(review.getStarRating())
                .review(review.getReview())
                .restaurant(review.getRestaurant())
                .build();
    }
}
