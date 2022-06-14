package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.ClothingProducts;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.repository.ClothingProductRepo;
import com.rent.project.productservice.request.format.ClothProduct;
import com.rent.project.productservice.services.ClothProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cloth-product/")
public class ClothProductController {

    @Autowired
    ClothProductService clothProductService;

    @Autowired
    ClothingProductRepo clothingProductRepo;


    @PostMapping(path = "/add-cloth-product",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> addClothProduct(@RequestPart("clothProduct") ClothProduct clothProduct, @RequestParam("file") MultipartFile file) throws IOException {
        return clothProductService.addClothProduct(clothProduct,file);
    }

    @GetMapping(path = "/get-cloth-product/")
    public ResponseEntity<Object> getClothingProduct(@RequestParam("cloth_product_id") Long cloth_product_id){
        try{
            return ResponseEntity.ok().body(clothProductService.getClothProduct(cloth_product_id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "Please select correct clothing product-id or exception occurred as : "+e));
        }

    }

    @GetMapping(path = "/get-categorywise-cproduct/")
    public ResponseEntity<Object> getSubcategoryProducts(@RequestParam("subcategory-id") Long subCategoryId){
        try{
            return ResponseEntity.ok().body(clothingProductRepo.getClothProductsByCategory(subCategoryId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "You have selected wrong category or exception occurred as : "+e));
        }

    }

    @GetMapping(path = "get/latest-products/")
    public ResponseEntity<Object> getFourWeekProducts(){
        try{
            List<ClothingProducts> clothingProducts = clothingProductRepo.getFourWeekClothProducts();
            return ResponseEntity.ok().body(clothingProducts);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "could not find latest products due to + "+e));
        }

    }

    @GetMapping(path = "/get/all-cproducts/")
    public ResponseEntity<Object> getAllProducts(){
        try {
            return ResponseEntity.ok().body(clothingProductRepo.findAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "could not find all products due to exception + "+e));
        }
    }

    @Transactional
    @PutMapping(path = "/update-size/")
    public ResponseEntity<Object> updateSize(@RequestParam("cp_id") Long cpId,@RequestBody() String size){
        try{
            clothingProductRepo.setSizeForClothingProduct(size,cpId);
            return ResponseEntity.ok().body(size);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "could not update products due to exception + "+e));
        }

    }

}
