package com.foodg.restaurant.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {


    @NotBlank(message = "Restaurant name is required")
    private String name;


    @NotBlank(message = "Address is required")
    private String address;


    @NotBlank(message = "Phone number is required")
    private String phone;


    private String imageUrl;


    private Long ownerId;

}