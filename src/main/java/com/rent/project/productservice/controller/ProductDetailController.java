package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.repository.ProductDetailRepo;
import com.rent.project.productservice.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productdetail")
public class ProductDetailController {

    @Autowired
    ProductDetailService pds;

    @PostMapping("/add")
    public ResponseEntity<ProductDetail> addproductdetail(@RequestBody ProductDetail productDetail)
    {
        return pds.addProductDetail(productDetail);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<ProductDetail> editproductdetail(@PathVariable Long Id, @RequestBody ProductDetail productDetail)
    {
        return pds.editProductDetail(Id,productDetail);
    }

    @DeleteMapping("/delete/{Id}")
    public void deleteproductdetail(@PathVariable Long Id)
    {
        pds.deleteProductDetail(Id);
    }

    @GetMapping("/all")
    public List<ProductDetail> getProductsDetail() {
        return pds.getProductDetail();
    }
}
