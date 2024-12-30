package com.example.travel_web.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TourDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private String duration;
    private String location;
    private Integer maxGroupSize;
    private Boolean isFeatured;
}