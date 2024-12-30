package com.example.travel_web.controller;

import com.example.travel_web.service.TourService;
import com.example.travel_web.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TourService tourService;

    @GetMapping("/")
    public String home(Model model) {
        try {
            List<TourDTO> featuredTours = tourService.getFeaturedTours();
            List<TourDTO> allTours = tourService.getAllTours();

            model.addAttribute("featuredTours", featuredTours);
            model.addAttribute("allTours", allTours);

            return "index";
        } catch (Exception e) {
            // Log the error
            System.out.println("Error loading tours: " + e.getMessage());
            model.addAttribute("error", "Error loading tours");
            return "error";
        }
    }

    // Agregar una ruta para el About Us
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // Agregar una ruta para la galería
    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    // Agregar una ruta para contactos
    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
}