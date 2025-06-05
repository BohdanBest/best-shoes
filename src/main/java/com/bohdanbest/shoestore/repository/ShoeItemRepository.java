package com.bohdanbest.shoestore.repository;

import com.bohdanbest.shoestore.entity.ShoeItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoeItemRepository extends CrudRepository<ShoeItem, Long> {
    @Query("SELECT si FROM ShoeItem si WHERE si.quantityInStock > 0")
    List<ShoeItem> findAllInStock();

    @Query("SELECT si FROM ShoeItem si WHERE si.quantityInStock = 0")
    List<ShoeItem> findAllOutOfStock();
}