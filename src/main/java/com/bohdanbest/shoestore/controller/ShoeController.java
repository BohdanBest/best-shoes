package com.bohdanbest.shoestore.controller;
import com.bohdanbest.shoestore.model.ShoeModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ShoeController {

    private final List<ShoeModel> shoes = Arrays.asList(
            new ShoeModel(1L, "Air Max", "Nike", 120.0, "Black", 42, "Running", true),
            new ShoeModel(2L, "Classic", "Adidas", 80.0, "White", 39, "Casual", false),
            new ShoeModel(3L, "Chuck Taylor", "Converse", 60.0, "Red", 40, "Casual", true),
            new ShoeModel(4L, "Gel-Kayano", "Asics", 140.0, "Blue", 41, "Running", false),
            new ShoeModel(5L, "Timberland", "Timberland", 180.0, "Brown", 43, "Boots", true)
    );

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("pages/index");
        modelAndView.addObject("shoes", shoes);
        modelAndView.addObject("title", "Shoe Store - Home");
        return modelAndView;
    }

    @GetMapping("/shoe")
    public ModelAndView shoeDetails(@RequestParam Long id) {
        ShoeModel shoe = shoes.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        ModelAndView modelAndView = new ModelAndView("shoe-details");
        modelAndView.addObject("shoe", shoe);
        modelAndView.addObject("title", "Shoe Details");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("pages/about");
        modelAndView.addObject("title", "About Us");
        return modelAndView;
    }
}