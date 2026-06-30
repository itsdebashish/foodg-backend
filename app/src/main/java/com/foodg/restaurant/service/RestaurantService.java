package com.foodg.restaurant.service;


import com.foodg.restaurant.dto.RestaurantRequest;
import com.foodg.restaurant.dto.UpdateRestaurantRequest;
import com.foodg.restaurant.dto.RestaurantResponse;
import com.foodg.restaurant.entity.Restaurant;

import java.util.List;


public interface RestaurantService {

    RestaurantResponse createRestaurant(
            RestaurantRequest request
    );

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurantById(
            Long id
    );

    RestaurantResponse updateRestaurant(
            Long id,
            UpdateRestaurantRequest request
    );

    void deleteRestaurant(
            Long id
    );

    List<RestaurantResponse> searchRestaurant(
            String keyword
    );

    List<RestaurantResponse> getTopRatedRestaurants();

    List<RestaurantResponse> getRestaurantsByOwner(
            Long ownerId
    );
}