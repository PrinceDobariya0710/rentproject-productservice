package com.rent.project.productservice.repository;

import com.rent.project.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query(value = "select * from product where user_details_id=?1",nativeQuery = true)
    List<Product> fetchUserProduct(Long id);

    @Query(value = "select * from product where subcategory_id=?1",nativeQuery = true)
    List<Product> fetchCategoryProduct(Long id);

}
