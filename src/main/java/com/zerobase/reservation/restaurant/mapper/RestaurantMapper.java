package com.zerobase.reservation.restaurant.mapper;

import com.zerobase.reservation.restaurant.entity.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    List<Restaurant> selectList();
}
