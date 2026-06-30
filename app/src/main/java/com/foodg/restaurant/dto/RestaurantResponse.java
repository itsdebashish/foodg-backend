package com.foodg.restaurant.dto;


import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {


    private Long id;


    private String name;


    private String address;


    private String phone;


    private String imageUrl;


    private Double rating;


    private Boolean active;


    private Long ownerId;


    private String ownerName;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}