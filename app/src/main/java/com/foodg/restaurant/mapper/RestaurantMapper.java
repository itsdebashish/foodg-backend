
package com.foodg.restaurant.mapper;


import com.foodg.restaurant.dto.RestaurantRequest;
import com.foodg.restaurant.dto.RestaurantResponse;
import com.foodg.restaurant.dto.UpdateRestaurantRequest;
import com.foodg.restaurant.entity.Restaurant;

import com.foodg.user.entity.User;
import org.springframework.stereotype.Component;



@Component
public class RestaurantMapper {

    public RestaurantResponse toResponse(
            Restaurant restaurant) {

        if (restaurant == null) {
            return null;
        }

        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .imageUrl(restaurant.getImageUrl())
                .rating(restaurant.getRating())
                .active(restaurant.getActive())
                .ownerId(
                        restaurant.getOwner() != null
                                ? restaurant.getOwner().getId()
                                : null
                )
                .ownerName(
                        restaurant.getOwner() != null
                                ? restaurant.getOwner().getName()
                                : null
                )
                .createdAt(
                        restaurant.getCreatedAt()
                )
                .updatedAt(
                        restaurant.getUpdatedAt()
                )
                .build();
    }


    public Restaurant fromRestaurant(RestaurantRequest request, User owner) {

        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .imageUrl(request.getImageUrl())
                .owner(owner)
                .rating(0.0)
                .active(true)
                .build();
    }

    public Restaurant fromUpdatedRestaurant(UpdateRestaurantRequest request) {
        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .imageUrl(request.getImageUrl())
                .build();
    }

}