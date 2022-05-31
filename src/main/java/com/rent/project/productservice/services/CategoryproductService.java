package com.rent.project.productservice.services;

import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.models.SubCategory;
import com.rent.project.productservice.repository.ProductDetailRepo;
import com.rent.project.productservice.repository.ProductRepo;
import com.rent.project.productservice.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryproductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductDetailRepo detailRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;
    public List<ProductDetail> getCategoryProduct(String Category)
    {
        final int[] i = new int[1];
        List<SubCategory> sb = subCategoryRepo.getsubcategoryData(Category);
        List<Product> pin = productRepo.fetchCategoryProduct(sb.get(0).getId());
        List<ProductDetail> pdmodel = new ArrayList<>();
        i[0] = 0;
        pin.forEach(s1->
        {
            pdmodel.add(detailRepo.fetchProductDetail(pin.get(i[0]).getId()));
            i[0] = i[0] + 1;
        });
        return pdmodel;
    }
}
