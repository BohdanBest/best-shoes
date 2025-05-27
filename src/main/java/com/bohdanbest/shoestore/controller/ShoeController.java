package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.model.ShoeItemDTO;
import com.bohdanbest.shoestore.entity.ShoeItem;
import com.bohdanbest.shoestore.repository.ShoeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ShoeController {

    @Autowired
    private ShoeItemRepository shoeItemRepository;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("pages/index");

        Iterable<ShoeItem> shoeItemsIterable = shoeItemRepository.findAll();

        List<ShoeItemDTO> shoeItems = StreamSupport.stream(shoeItemsIterable.spliterator(), false)
                .map(this::convertToDTO)
                .toList();

        List<ShoeItemDTO> inStockShoeItems = shoeItems.stream()
                .filter(ShoeItemDTO::isInStock)
                .collect(Collectors.toList());

        List<ShoeItemDTO> outOfStockShoeItems = shoeItems.stream()
                .filter(shoe -> !shoe.isInStock())
                .collect(Collectors.toList());

        modelAndView.addObject("inStockShoeItems", inStockShoeItems);
        modelAndView.addObject("outOfStockShoeItems", outOfStockShoeItems);
        modelAndView.addObject("title", "Shoe Store - Home");
        return modelAndView;
    }

    @GetMapping("/shoe")
    public ModelAndView shoeDetails(@RequestParam Long id) {
        ShoeItem shoeItem = shoeItemRepository.findById(id).orElse(null);
        ShoeItemDTO shoe = shoeItem != null ? convertToDTO(shoeItem) : null;

        ModelAndView modelAndView = new ModelAndView("shoe-details");
        modelAndView.addObject("shoe", shoe);
        modelAndView.addObject("title", shoe != null ? shoe.getShoeModelName() + " - Details" : "Shoe Details");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("pages/about");
        modelAndView.addObject("title", "About Us");
        return modelAndView;
    }

    private ShoeItemDTO convertToDTO(ShoeItem shoeItem) {
        return new ShoeItemDTO(
                shoeItem.getId(),
                shoeItem.getShoeModel().getName(),
                shoeItem.getShoeModel().getBrand().getName(),
                shoeItem.getShoeModel().getCategory().getName(),
                shoeItem.getSize(),
                shoeItem.getColor(),
                shoeItem.getPrice(),
                shoeItem.getShoeModel().getMainImageUrl(),
                shoeItem.getQuantityInStock() > 0
        );
    }
}