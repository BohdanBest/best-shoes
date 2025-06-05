package com.bohdanbest.shoestore.model;

public class ShoeItemDTO {

    private Long id;
    private String shoeModelName;
    private String brandName;
    private String categoryName;
    private String size;
    private String color;
    private Double price;
    private String imageUrl;
    private boolean inStock;
    private String description;
    private Integer quantityInStock;

    public ShoeItemDTO(Long id, String shoeModelName, String brandName, String categoryName, String size, String color, Double price, String imageUrl, boolean inStock, String description, Integer quantityInStock) {
        this.id = id;
        this.shoeModelName = shoeModelName;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.size = size;
        this.color = color;
        this.price = price;
        this.imageUrl = imageUrl;
        this.inStock = inStock;
        this.description = description;
        this.quantityInStock = quantityInStock;
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

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(Integer quantityInStock) { this.quantityInStock = quantityInStock; }
}