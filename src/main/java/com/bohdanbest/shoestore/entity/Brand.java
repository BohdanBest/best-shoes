package com.bohdanbest.shoestore.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoeModel> shoeModels;

    public Brand() {}

    public Brand(String name, String countryOfOrigin, String description) {
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<ShoeModel> getShoeModels() { return shoeModels; }
    public void setShoeModels(List<ShoeModel> shoeModels) { this.shoeModels = shoeModels; }
}
