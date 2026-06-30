package com.foodg.restaurant.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRestaurantRequest {


    @NotBlank(message = "Restaurant name is required")
    private String name;


    @NotBlank(message = "Address is required")
    private String address;


    private String phone;


    private String imageUrl;

}