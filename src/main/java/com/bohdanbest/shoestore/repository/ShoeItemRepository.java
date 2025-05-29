package com.bohdanbest.shoestore.repository;

import com.bohdanbest.shoestore.entity.ShoeItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoeItemRepository extends CrudRepository<ShoeItem, Long> {
    List<ShoeItem> findByShoeModelId(Long shoeModelId);
    List<ShoeItem> findByQuantityInStockGreaterThan(Integer quantity);
    ShoeItem findBySkuCode(String skuCode);

}