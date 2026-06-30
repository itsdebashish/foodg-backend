package com.foodg.restaurant.service;


import com.foodg.restaurant.dto.RestaurantRequest;
import com.foodg.restaurant.dto.RestaurantResponse;
import com.foodg.restaurant.dto.UpdateRestaurantRequest;
import com.foodg.restaurant.service.RestaurantService;

import java.util.List;

import com.foodg.restaurant.entity.Restaurant;
import com.foodg.restaurant.mapper.RestaurantMapper;
import com.foodg.restaurant.repository.RestaurantRepository;
import com.foodg.user.entity.User;
import com.foodg.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final UserRepository userRepository;

    @Override
    public RestaurantResponse createRestaurant(
            RestaurantRequest request) {

        User owner = userRepository.findById(
                        request.getOwnerId()
                )
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        Restaurant restaurant = restaurantMapper.fromRestaurant(request,owner);

        Restaurant saved =
                restaurantRepository.save(restaurant);

        return restaurantMapper.toResponse(saved);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {

        return restaurantRepository
                .findAll()
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(
            Long id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        return restaurantMapper.toResponse(
                restaurant
        );
    }

    @Override
    public RestaurantResponse updateRestaurant(
            Long id,
            UpdateRestaurantRequest request) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));
        restaurant = restaurantMapper.fromUpdatedRestaurant(request);

        Restaurant updated =
                restaurantRepository.save(
                        restaurant
                );
        return restaurantMapper.toResponse(
                updated
        );
    }

    @Override
    public void deleteRestaurant(
            Long id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        restaurant.setActive(false);

        restaurantRepository.save(
                restaurant
        );
    }

    @Override
    public List<RestaurantResponse> searchRestaurant(
            String keyword) {

        return restaurantRepository
                .findByNameContainingIgnoreCase(
                        keyword
                )
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    @Override
    public List<RestaurantResponse> getTopRatedRestaurants() {

        return restaurantRepository
                .findTop10ByOrderByRatingDesc()
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    @Override
    public List<RestaurantResponse> getRestaurantsByOwner(
            Long ownerId) {

        return restaurantRepository
                .findByOwnerId(ownerId)
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

}