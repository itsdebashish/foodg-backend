package com.foodg.restaurant.entity;
import com.foodg.menu.entity.MenuItem;
import com.foodg.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.net.UnknownServiceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String imageUrl;

    private Double rating;

    private Boolean active;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}