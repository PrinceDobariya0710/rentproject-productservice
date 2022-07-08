package com.rent.project.productservice.repository;

import com.rent.project.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query(value = "select * from product where user_details_id=?1",nativeQuery = true)
    List<Product> fetchUserProduct(Long id);

    @Query(value = "select * from product where subcategory_id=?1",nativeQuery = true)
    List<Product> fetchCategoryProduct(Long id);

    @Modifying
    @Query("UPDATE Product p SET p.product_image=?2 WHERE p.id=?1")
    void updateProductImage(Long id,String productImage);

    @Modifying
    @Query(value = "UPDATE product p SET p.available_pieces=?2 WHERE p.id=?1",nativeQuery = true)
    void updateAvailablePieces(Long id,Integer availablePieces);
}
