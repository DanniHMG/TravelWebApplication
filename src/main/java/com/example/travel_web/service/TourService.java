package com.example.travel_web.service;

import com.example.travel_web.dto.TourDTO;
import com.example.travel_web.model.Tour;
import com.example.travel_web.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public List<TourDTO> getAllTours() {
        return tourRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TourDTO> getFeaturedTours() {
        return tourRepository.findByIsFeaturedTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TourDTO getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        return convertToDTO(tour);
    }

    // Modificar este método para usar DTO
    public TourDTO saveTour(TourDTO tourDTO) {
        Tour tour = convertToEntity(tourDTO);
        Tour savedTour = tourRepository.save(tour);
        return convertToDTO(savedTour);
    }

    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    private TourDTO convertToDTO(Tour tour) {
        TourDTO dto = new TourDTO();
        BeanUtils.copyProperties(tour, dto);
        return dto;
    }

    private Tour convertToEntity(TourDTO tourDTO) {
        Tour tour = new Tour();
        BeanUtils.copyProperties(tourDTO, tour);
        return tour;
    }
}