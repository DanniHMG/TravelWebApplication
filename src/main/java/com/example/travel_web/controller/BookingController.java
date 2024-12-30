package com.example.travel_web.controller;

import com.example.travel_web.dto.BookingDTO;
import com.example.travel_web.model.Booking;
import com.example.travel_web.service.BookingService;
import com.example.travel_web.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TourService tourService;

    @GetMapping("/tour/{tourId}")
    public String showBookingForm(@PathVariable Long tourId, Model model) {
        model.addAttribute("tour", tourService.getTourById(tourId));
        model.addAttribute("bookingDTO", new BookingDTO());
        return "booking-form";
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute BookingDTO bookingDTO) {
        Booking booking = bookingService.createBooking(bookingDTO);
        return "redirect:/booking/confirmation/" + booking.getId();
    }

    @GetMapping("/confirmation/{id}")
    public String showConfirmation(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingService.getBooking(id));
        return "booking-confirmation";
    }
}