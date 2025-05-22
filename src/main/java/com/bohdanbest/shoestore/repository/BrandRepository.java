package com.bohdanbest.shoestore.repository;

import com.bohdanbest.shoestore.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findByName(String name);
}