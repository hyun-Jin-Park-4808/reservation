package com.zerobase.reservation.restaurant.controller;

import com.zerobase.reservation.restaurant.dto.RestaurantDetail;
import com.zerobase.reservation.restaurant.dto.RestaurantInfo;
import com.zerobase.reservation.restaurant.dto.RestaurantListDto;
import com.zerobase.reservation.restaurant.dto.RestaurantRegisterDto;
import com.zerobase.reservation.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register") // 식당 등록 가입
    public RestaurantRegisterDto.Response register(@RequestBody RestaurantRegisterDto.Request request) {
        return RestaurantRegisterDto.Response.from(
                restaurantService.register(
                        request.getUserId(),
                        request.getRestaurantName(),
                        request.getLocationX(),
                        request.getLocationY(),
                        request.getDetail()
                )
        );
    }

    @GetMapping("/restaurant/detail") // 식당 상세 정보 조회
    public RestaurantDetail.Response detail(@RequestBody RestaurantDetail.Request request) {
        return RestaurantDetail.Response.from(
                restaurantService.detail(
                        request.getRestaurantId(),
                        request.getRestaurantName()
                )
        );
    }


    @GetMapping("/restaurant/list/Dictionary") // 식당 조회 - 가나다 순
    public List<RestaurantInfo> getRestaurantsOrderByDictionary() {
        return restaurantService.getRestaurantsOrderByDictionary()
                .stream().map(Restaurant
                        -> RestaurantInfo.builder()
                        .restaurantName(Restaurant.getRestaurantName())
                        .locationX(Restaurant.getLocationX())
                        .locationY(Restaurant.getLocationY())
                        .detail(Restaurant.getDetail())
                        .starRatings(Restaurant.getStarRatings())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/restaurant/list/star") // 식당 조회 - 별점 순
    public List<RestaurantInfo> getRestaurantsOrderByStarRatings() {
        return restaurantService.getRestaurantsOrderByStarRatings()
                .stream().map(Restaurant
                        -> RestaurantInfo.builder()
                        .restaurantName(Restaurant.getRestaurantName())
                        .locationX(Restaurant.getLocationX())
                        .locationY(Restaurant.getLocationY())
                        .detail(Restaurant.getDetail())
                        .starRatings(Restaurant.getStarRatings())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/restaurant/list/distance") // 식당 조회 - 거리 순
    public List<RestaurantInfo> getRestaurantsOrderByDistance(
            @RequestBody RestaurantListDto.Request request) {

        return restaurantService.getRestaurantsOrderByDistance(request.getLocationX(), request.getLocationY())
                .stream().map(Restaurant
                        -> RestaurantInfo.builder()
                        .restaurantName(Restaurant.getRestaurantName())
                        .locationX(Restaurant.getLocationX())
                        .locationY(Restaurant.getLocationY())
                        .distance(Restaurant.getDistance())
                        .detail(Restaurant.getDetail())
                        .starRatings(Restaurant.getStarRatings())
                        .build())
                .collect(Collectors.toList());
    }


}
