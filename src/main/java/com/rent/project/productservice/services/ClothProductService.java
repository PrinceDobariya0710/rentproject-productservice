package com.rent.project.productservice.services;

import com.rent.project.productservice.models.ClothingProducts;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.repository.ClothingProductRepo;
import com.rent.project.productservice.repository.ProductRepo;
import com.rent.project.productservice.request.format.ClothProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class ClothProductService {

    @Autowired
    ProductRepo productrepo;

    @Autowired
    ClothingProductRepo clothingProductRepo;

    @Autowired
    FileUploadService fileUploadService;
    //add clothing category products
    public ClothingProducts addClothProduct(ClothProduct clothProduct) {
        Date createdAt = Date.valueOf(LocalDate.now());
        Date modifiedAt = Date.valueOf(LocalDate.now());

        Product product = new Product(
                clothProduct.getUserDetailsId(),
                clothProduct.getProductName(),
                clothProduct.getValue_duration(),
                "Not set",
                clothProduct.getProduct_description(),
                clothProduct.getDeposit(),
                clothProduct.getAvailable_pieces(),
                clothProduct.getProduct_rate(),
                clothProduct.getProductDetail(),
                clothProduct.getSubcategory(),
                clothProduct.getProductDurationRates(),
                createdAt,
                modifiedAt
        );

        Product savedProduct = productrepo.save(product);
        ClothingProducts clothingProducts = new ClothingProducts(savedProduct, clothProduct.getSize());
        ClothingProducts savedClothingProduct = clothingProductRepo.save(clothingProducts);

        return savedClothingProduct;
    }

    //get clothing product
    public Optional<ClothingProducts> getClothProduct(Long cloth_product_id) {
        return clothingProductRepo.findById(cloth_product_id);
    }



}
