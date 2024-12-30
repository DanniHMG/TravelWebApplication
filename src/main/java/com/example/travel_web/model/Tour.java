package com.example.travel_web.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private BigDecimal price;

    private String duration;

    private String location;

    @Column(name = "max_group_size")
    private Integer maxGroupSize;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;
}
