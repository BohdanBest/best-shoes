package com.bohdanbest.shoestore.model;

public class ShoeModel {
    public final Long id;
    public final String name;
    public final String brand;
    public final double price;
    public final String color;
    public final int size;
    public final String category;
    public boolean inStock;

    public ShoeModel(Long id, String name, String brand, double price, String color, int size, String category, boolean inStock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.size = size;
        this.category = category;
        this.inStock = inStock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String getCategory() {
        return category;
    }
    public boolean getInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", category='" + category + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}