package com.bohdanbest.shoestore.repository;

import com.bohdanbest.shoestore.entity.ShoeItem;
import com.bohdanbest.shoestore.entity.ShoeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoeItemRepository extends CrudRepository<ShoeItem, Long> {
    List<ShoeItem> findByShoeModelId(Long shoeModelId);
    List<ShoeItem> findByQuantityInStockGreaterThan(Integer quantity);
    ShoeItem findBySkuCode(String skuCode);

    @Query("SELECT sm FROM ShoeModel sm WHERE EXISTS (SELECT si FROM ShoeItem si WHERE si.shoeModel = sm AND si.quantityInStock > 0)")
    List<ShoeModel> findAllInStock();

    @Query("SELECT sm FROM ShoeModel sm WHERE NOT EXISTS (SELECT si FROM ShoeItem si WHERE si.shoeModel = sm AND si.quantityInStock > 0)")
    List<ShoeModel> findAllOutOfStock();
}