package com.example.travel_web.service;

import com.example.travel_web.dto.BookingDTO;
import com.example.travel_web.exception.ValidationException;
import com.example.travel_web.model.Booking;
import com.example.travel_web.model.Tour;
import com.example.travel_web.repository.BookingRepository;
import com.example.travel_web.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TourRepository tourRepository;

    public Booking createBooking(BookingDTO bookingDTO) {
        validateBooking(bookingDTO);

        Tour tour = tourRepository.findById(bookingDTO.getTourId())
                .orElseThrow(() -> new ValidationException("Tour not found"));

        Booking booking = new Booking();
        booking.setTour(tour);
        booking.setCustomerName(bookingDTO.getCustomerName());
        booking.setCustomerEmail(bookingDTO.getCustomerEmail());
        booking.setCustomerPhone(bookingDTO.getCustomerPhone());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setNumberOfPeople(bookingDTO.getNumberOfPeople());

        BigDecimal totalPrice = tour.getPrice().multiply(BigDecimal.valueOf(bookingDTO.getNumberOfPeople()));
        booking.setTotalPrice(totalPrice);

        return bookingRepository.save(booking);
    }

    private void validateBooking(BookingDTO booking) {
        if (booking.getBookingDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Booking date cannot be in the past");
        }

        if (booking.getNumberOfPeople() < 1) {
            throw new ValidationException("Number of people must be at least 1");
        }

        if (booking.getCustomerName() == null || booking.getCustomerName().trim().isEmpty()) {
            throw new ValidationException("Customer name is required");
        }

        if (booking.getCustomerEmail() == null || !booking.getCustomerEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("Valid email is required");
        }
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Booking not found"));
    }
}
