package com.zerobase.reservation.restaurant.service;

import com.zerobase.reservation.exception.impl.NoMemberException;
import com.zerobase.reservation.restaurant.dto.RestaurantDto;
import com.zerobase.reservation.restaurant.entity.Restaurant;
import com.zerobase.reservation.restaurant.mapper.RestaurantMapper;
import com.zerobase.reservation.restaurant.repository.RestaurantRepository;
import com.zerobase.reservation.member.entity.Member;
import com.zerobase.reservation.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    @Transactional
    public RestaurantDto register(String userId, String restaurantName,
                                  String locationX, String locationY, String detail) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (optionalMember.isEmpty() || optionalMember.get().isAdminYN() == false) {
            throw new NoMemberException();
        }

        return RestaurantDto.fromEntity(
                restaurantRepository.save(Restaurant.builder()
                                .userId(userId)
                                .restaurantName(restaurantName)
                                .locationX(locationX)
                                .locationY(locationY)
                                .detail(detail)
                                .starRatings("")
                                .reviews(null)
                        .build()
                )
        );
    }

    @Override
    @Transactional
    public RestaurantDto detail(int restaurantId, String restaurantName) {

        RestaurantDto restaurantDto = new RestaurantDto();
        Restaurant restaurant = restaurantRepository.findByIdAndRestaurantName(restaurantId, restaurantName);
        // JPA 쿼리로 반환되는 타입은 entity 객체인 Restaurant이므로, RestaurantDto 객체로 반환하려면 아래처럼 한 번 더 매핑을 해줘야 한다!
        return restaurantDto.fromEntity(restaurant);
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurantsOrderByDictionary() {

        return restaurantRepository.findAllByOrderByRestaurantNameAsc();
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurantsOrderByStarRatings() {

        return restaurantRepository.findAllByOrderByStarRatingsAsc();
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurantsOrderByDistance(String x, String y) {
        double R = 6371.0;
        double toRadian = Math.PI / 180;
        double myLocationX = Double.parseDouble(x);
        double myLocationY = Double.parseDouble(y);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        for (Restaurant restaurant : restaurants) {
            int id = restaurant.getId();
            String detail = restaurant.getDetail();
            String restaurantName = restaurant.getRestaurantName();

            String starRatings = restaurant.getStarRatings();
            String userId = restaurant.getUserId();

            double locationX = Double.parseDouble(restaurant.getLocationX());
            double locationY = Double.parseDouble(restaurant.getLocationY());

            double dX = Math.abs(locationX - myLocationX) * toRadian;
            double dY = Math.abs(locationY - myLocationY) * toRadian;

            double sinDeltaX = Math.sin(dX / 2);
            double sinDeltaY = Math.sin(dY / 2);

            double squareRoot = Math.sqrt(
                    sinDeltaX * sinDeltaY
                            + Math.cos(locationX * toRadian) * Math.cos(myLocationX * toRadian) * sinDeltaX * sinDeltaY);

            String distance = Double.toString(2 * R * Math.asin(squareRoot));

            restaurantRepository.save(Restaurant.builder()
                            .id(id)
                            .detail(detail)
                            .restaurantName(restaurantName)
                            .starRatings(starRatings)
                            .locationX(Double.toString(locationX))
                            .locationY(Double.toString(locationY))
                            .userId(userId)
                            .distance(distance)
                    .build()
            );
        }
        return restaurantMapper.selectList();
    }

}
