package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.entity.*;
import com.bohdanbest.shoestore.model.ShoeItemDTO;
import com.bohdanbest.shoestore.repository.BrandRepository;
import com.bohdanbest.shoestore.repository.CategoryRepository;
import com.bohdanbest.shoestore.repository.ShoeItemRepository;
import com.bohdanbest.shoestore.repository.ShoeModelRepository;
import com.bohdanbest.shoestore.security.AuthenticationInfoFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ShoeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoeController.class);

    @Autowired
    private ShoeItemRepository shoeItemRepository;

    @Autowired
    private ShoeModelRepository shoeModelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthenticationInfoFacade authFacade;

    @GetMapping("/")
    public ModelAndView home(@RequestParam(required = false) String success) {
        ModelAndView modelAndView = new ModelAndView("pages/index");

        List<ShoeItemDTO> inStockShoeItems = shoeItemRepository.findAllInStock()
                .stream()
                .map(this::convertToDTO)
                .toList();

        List<ShoeItemDTO> outOfStockShoeItems = shoeItemRepository.findAllOutOfStock()
                .stream()
                .map(this::convertToDTO)
                .toList();

        modelAndView.addObject("inStockShoeItems", inStockShoeItems);
        modelAndView.addObject("outOfStockShoeItems", outOfStockShoeItems);
        modelAndView.addObject("title", "Shoe Store - Home");
        modelAndView.addObject("authFacade", authFacade);
        if (success != null) {
            modelAndView.addObject("success", success);
        }
        return modelAndView;
    }

    @GetMapping("/shoe")
    public ModelAndView shoeDetails(@RequestParam Long id) {
        ShoeItem shoeItem = shoeItemRepository.findById(id).orElse(null);
        ShoeItemDTO shoe = shoeItem != null ? convertToDTO(shoeItem) : null;

        ModelAndView modelAndView = new ModelAndView("shoe-details");
        modelAndView.addObject("shoe", shoe);
        modelAndView.addObject("title", shoe != null ? shoe.getShoeModelName() + " - Details" : "Shoe Details");
        modelAndView.addObject("authFacade", authFacade);
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("pages/about");
        modelAndView.addObject("title", "About Us");
        modelAndView.addObject("authFacade", authFacade);
        return modelAndView;
    }

    @GetMapping("/shoe-item/add")
    public ModelAndView showAddShoeItemForm() {
        LOGGER.info("Accessing add shoe item form by user: {}", authFacade.getUsername());
        ModelAndView modelAndView = new ModelAndView("pages/add-shoe");
        modelAndView.addObject("title", "Add New Shoe Item");
        modelAndView.addObject("categories", categoryRepository.findAll());
        modelAndView.addObject("authFacade", authFacade);
        return modelAndView;
    }

    @PostMapping("/api/shoe-item/add")
    public RedirectView addShoeItem(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String modelName,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer quantityInStock,
            @RequestParam(required = false) String imageUrl
    ) {
        try {
            LOGGER.info("Attempting to add new shoe item for brand: {}, model: {} by user: {}", brandName, modelName, authFacade.getUsername());

            if (brandName == null || brandName.trim().isEmpty()) {
                LOGGER.warn("Brand name is missing");
                return new RedirectView("/shoe-item/add?error=Brand name is required");
            }

            if (modelName == null || modelName.trim().isEmpty()) {
                LOGGER.warn("Model name is missing");
                return new RedirectView("/shoe-item/add?error=Model name is required");
            }

            if (categoryId == null) {
                LOGGER.warn("Category is missing");
                return new RedirectView("/shoe-item/add?error=Category is required");
            }

            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isEmpty()) {
                LOGGER.warn("Invalid category ID: {}", categoryId);
                return new RedirectView("/shoe-item/add?error=Invalid category");
            }
            Category category = categoryOptional.get();

            if (size == null || size.trim().isEmpty()) {
                LOGGER.warn("Size is missing");
                return new RedirectView("/shoe-item/add?error=Size is required");
            }

            if (color == null || color.trim().isEmpty()) {
                LOGGER.warn("Color is missing");
                return new RedirectView("/shoe-item/add?error=Color is required");
            }

            if (price == null || price <= 0) {
                LOGGER.warn("Invalid price: {}", price);
                return new RedirectView("/shoe-item/add?error=Price must be greater than 0");
            }

            if (quantityInStock == null || quantityInStock < 0) {
                LOGGER.warn("Invalid quantity: {}", quantityInStock);
                return new RedirectView("/shoe-item/add?error=Quantity cannot be negative");
            }

            final String trimmedBrandName = brandName.trim();
            final String trimmedModelName = modelName.trim();
            final String trimmedDescription = description != null ? description.trim() : "";
            final String trimmedSize = size.trim();
            final String trimmedColor = color.trim();
            final String trimmedImageUrl = imageUrl != null ? imageUrl.trim() : "";

            Optional<Brand> brandOptional = brandRepository.findByName(trimmedBrandName);
            Brand brand = brandOptional.orElseGet(() -> {
                Brand newBrand = new Brand();
                newBrand.setName(trimmedBrandName);
                newBrand.setCountryOfOrigin("Unknown");
                return brandRepository.save(newBrand);
            });

            Optional<ShoeModel> shoeModelOptional = shoeModelRepository.findByNameAndBrandId(trimmedModelName, brand.getId());
            ShoeModel shoeModel = shoeModelOptional.orElseGet(() -> {
                ShoeModel newModel = new ShoeModel();
                newModel.setName(trimmedModelName);
                newModel.setBrand(brand);
                newModel.setCategory(category);
                newModel.setDescription(trimmedDescription);
                newModel.setBasePrice(BigDecimal.valueOf(price));
                newModel.setImageUrl(!trimmedImageUrl.isEmpty() ? trimmedImageUrl : "");
                return shoeModelRepository.save(newModel);
            });

            Iterable<ShoeItem> existingItems = shoeItemRepository.findAll();
            for (ShoeItem item : existingItems) {
                if (item.getShoeModel().getId().equals(shoeModel.getId()) &&
                        item.getSize().equalsIgnoreCase(trimmedSize) &&
                        item.getColor().equalsIgnoreCase(trimmedColor)) {
                    LOGGER.warn("Shoe item already exists for model ID: {}, size: {}, color: {}", shoeModel.getId(), trimmedSize, trimmedColor);
                    return new RedirectView("/shoe-item/add?error=Shoe item already exists");
                }
            }

            ShoeItem shoeItem = new ShoeItem();
            shoeItem.setShoeModel(shoeModel);
            shoeItem.setSize(trimmedSize);
            shoeItem.setColor(trimmedColor);
            shoeItem.setPrice(price);
            shoeItem.setQuantityInStock(quantityInStock);
            shoeItem.setImageUrl(!trimmedImageUrl.isEmpty() ? trimmedImageUrl : shoeModel.getImageUrl());
            shoeItem.setSkuCode(generateSkuCode(shoeModel.getName(), trimmedSize, trimmedColor));
            shoeItem.setAdditionalImageUrls("");

            shoeItemRepository.save(shoeItem);

            ShoeItem savedItem = shoeItemRepository.findById(shoeItem.getId()).orElse(null);
            if (savedItem != null) {
                LOGGER.info("Shoe item saved successfully with ID: {}", shoeItem.getId());
                return new RedirectView("/?success=Shoe item added successfully");
            } else {
                LOGGER.error("Failed to save shoe item for model: {}", trimmedModelName);
                return new RedirectView("/shoe-item/add?error=Failed to add shoe item");
            }
        } catch (Exception e) {
            LOGGER.error("Error adding shoe item: {}", e.getMessage(), e);
            String errorMessage = "Server error occurred";
            return new RedirectView("/shoe-item/add?error=" + errorMessage.replaceAll("[\\r\\n]", ""));
        }
    }

    private String generateSkuCode(String modelName, String size, String color) {
        String base = (modelName + "-" + size + "-" + color).toUpperCase().replaceAll("[^A-Z0-9]", "");
        return base.substring(0, Math.min(base.length(), 10)) + "-" + UUID.randomUUID().toString().substring(0, 8);
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
                shoeItem.getImageUrl() != null ? shoeItem.getImageUrl() : shoeItem.getShoeModel().getImageUrl(),
                shoeItem.getQuantityInStock() > 0,
                shoeItem.getShoeModel().getDescription()
        );
    }
}