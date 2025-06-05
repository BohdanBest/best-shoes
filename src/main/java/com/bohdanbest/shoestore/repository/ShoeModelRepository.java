package com.bohdanbest.shoestore.repository;

import com.bohdanbest.shoestore.entity.ShoeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoeModelRepository extends CrudRepository<ShoeModel, Long> {
    Optional<ShoeModel> findByNameAndBrandId(String name, Long brandId);
}