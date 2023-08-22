package com.zerobase.reservation.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    @Column(name = "restaurant_id")
    private int id;

    private String userId; // email
    private String restaurantName;
    private String locationX; // 상점 X좌표
    private String locationY; // 상점 Y좌표
    private String detail; // 상점 설명
    private String starRatings; // 별점 평균
    private String distance; // 거리 계산 후 업데이트

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

}
