package com.example.travel_web.controller;

import com.example.travel_web.dto.TourDTO;
import com.example.travel_web.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TourService tourService;

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        return "admin/dashboard";
    }

    @GetMapping("/tour/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tour", new TourDTO());
        return "admin/tour-form";
    }

    @PostMapping("/tour/save")
    public String saveTour(@ModelAttribute TourDTO tourDTO) {
        tourService.saveTour(tourDTO);
        return "redirect:/admin";
    }

    @GetMapping("/tour/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("tour", tourService.getTourById(id));
        return "admin/tour-form";
    }

    @GetMapping("/tour/delete/{id}")
    public String deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return "redirect:/admin";
    }
}