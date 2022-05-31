package com.rent.project.productservice.repository;

import com.rent.project.productservice.models.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInventoryRepo extends JpaRepository<ProductInventory,Long> {

    @Query(value="select * from product_inventory where created_at >= now() - INTERVAL 30 day",nativeQuery = true)
    List<ProductInventory> fetchLatestProduct();
}
