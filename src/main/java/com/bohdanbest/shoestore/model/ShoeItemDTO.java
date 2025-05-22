package com.bohdanbest.shoestore.model;

import java.math.BigDecimal;

public class ShoeItemDTO {
    private Long id;
    private String shoeModelName;
    private String brandName;
    private String categoryName;
    private String size;
    private String color;
    private BigDecimal price;
    private String imageUrl;
    private boolean inStock;

    public ShoeItemDTO() {}

    public ShoeItemDTO(Long id, String shoeModelName, String brandName, String categoryName,
                       String size, String color, BigDecimal price, String imageUrl, boolean inStock) {
        this.id = id;
        this.shoeModelName = shoeModelName;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.size = size;
        this.color = color;
        this.price = price;
        this.imageUrl = imageUrl;
        this.inStock = inStock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getShoeModelName() { return shoeModelName; }
    public void setShoeModelName(String shoeModelName) { this.shoeModelName = shoeModelName; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getImageUrl() {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            if (imageUrl.startsWith("http") || imageUrl.startsWith("/images/")) {
                return imageUrl;
            }
            return "/images/shoes/" + imageUrl;
        }
        return "/images/shoes/placeholder.jpg";
    }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
}