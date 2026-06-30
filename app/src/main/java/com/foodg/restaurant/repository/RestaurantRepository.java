package com.foodg.restaurant.repository;


import com.foodg.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository
        extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByNameContainingIgnoreCase(
            String keyword
    );

    List<Restaurant> findTop10ByOrderByRatingDesc();

    List<Restaurant> findByOwnerId(
            Long ownerId
    );

}