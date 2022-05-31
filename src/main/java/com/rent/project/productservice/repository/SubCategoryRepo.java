package com.rent.project.productservice.repository;

import com.rent.project.productservice.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {

    @Query(value = "select * from sub_category where sub_category_name=?1",nativeQuery = true)
    List<SubCategory> getsubcategoryData(String category);
}
