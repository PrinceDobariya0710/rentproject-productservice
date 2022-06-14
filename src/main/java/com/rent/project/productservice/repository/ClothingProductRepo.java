package com.rent.project.productservice.repository;

import com.rent.project.productservice.models.ClothingProducts;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClothingProductRepo extends JpaRepository<ClothingProducts,Long> {

    @Override
    Optional<ClothingProducts> findById(Long aLong);

    @Modifying
    @Query("update ClothingProducts cp set cp.size = ?1 where cp.id = ?2")
    void setSizeForClothingProduct(String size, Long id);

    @Query("select cp from ClothingProducts cp , Product p , SubCategory sg where p.subcategory = sg.id and cp.product = p.id and sg.id = ?1")
    List<ClothingProducts> getClothProductsByCategory(Long subCategoryId);

    @Query(value = "SELECT * FROM clothing_products cp,product p WHERE cp.product_id=p.id AND p.created_at > date_sub(curdate(),INTERVAL 4 WEEK)",nativeQuery = true)
    List<ClothingProducts> getFourWeekClothProducts();

    @Override
    Page<ClothingProducts> findAll(Pageable pageable);

}
