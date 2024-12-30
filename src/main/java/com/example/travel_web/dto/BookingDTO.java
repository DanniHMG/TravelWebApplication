package com.example.travel_web.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDTO {
    private Long tourId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDate bookingDate;
    private Integer numberOfPeople;
}
