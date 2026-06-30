package com.foodg.restaurant.controller;

import com.foodg.restaurant.dto.*;
import com.foodg.restaurant.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @Valid @RequestBody RestaurantRequest request) {

        RestaurantResponse response =
                restaurantService.createRestaurant(request);

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant() {

        return ResponseEntity.ok(
                restaurantService.getAllRestaurants()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantDetail(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                restaurantService.getRestaurantById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRestaurantRequest request) {

        return ResponseEntity.ok(
                restaurantService.updateRestaurant(
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantResponse>> searchRestaurant(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                restaurantService.searchRestaurant(keyword)
        );
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<RestaurantResponse>> getTopRatedRestaurant() {

        return ResponseEntity.ok(
                restaurantService.getTopRatedRestaurants()
        );
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RestaurantResponse>> getOwnerRestaurants(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                restaurantService.getRestaurantsByOwner(ownerId)
        );
    }

}