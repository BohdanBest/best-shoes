package com.bohdanbest.shoestore.repository;

import com.bohdanbest.shoestore.entity.ShoeItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoeItemRepository extends CrudRepository<ShoeItem, Long> {
    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock > 0")
    List<ShoeItem> findAllInStock();

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock = 0")
    List<ShoeItem> findAllOutOfStock();

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock > 0 ORDER BY s.price ASC")
    List<ShoeItem> findAllInStockByOrderByPriceAsc();

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock > 0 ORDER BY s.price DESC")
    List<ShoeItem> findAllInStockByOrderByPriceDesc();

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock = 0 ORDER BY s.price ASC")
    List<ShoeItem> findAllOutOfStockByOrderByPriceAsc();

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock = 0 ORDER BY s.price DESC")
    List<ShoeItem> findAllOutOfStockByOrderByPriceDesc();

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock > 0 AND LOWER(s.shoeModel.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ShoeItem> findInStockByModelNameContaining(@Param("query") String query);

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock = 0 AND LOWER(s.shoeModel.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ShoeItem> findOutOfStockByModelNameContaining(@Param("query") String query);

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock > 0 AND LOWER(s.shoeModel.name) LIKE LOWER(CONCAT('%', :query, '%')) ORDER BY s.price ASC")
    List<ShoeItem> findInStockByModelNameContainingOrderByPriceAsc(@Param("query") String query);

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock > 0 AND LOWER(s.shoeModel.name) LIKE LOWER(CONCAT('%', :query, '%')) ORDER BY s.price DESC")
    List<ShoeItem> findInStockByModelNameContainingOrderByPriceDesc(@Param("query") String query);

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock = 0 AND LOWER(s.shoeModel.name) LIKE LOWER(CONCAT('%', :query, '%')) ORDER BY s.price ASC")
    List<ShoeItem> findOutOfStockByModelNameContainingOrderByPriceAsc(@Param("query") String query);

    @Query("SELECT s FROM ShoeItem s WHERE s.quantityInStock = 0 AND LOWER(s.shoeModel.name) LIKE LOWER(CONCAT('%', :query, '%')) ORDER BY s.price DESC")
    List<ShoeItem> findOutOfStockByModelNameContainingOrderByPriceDesc(@Param("query") String query);

    Long countByShoeModelId(Long modelId);
}