package com.example.travel_web.repository;

import com.example.travel_web.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByIsFeaturedTrue();
}