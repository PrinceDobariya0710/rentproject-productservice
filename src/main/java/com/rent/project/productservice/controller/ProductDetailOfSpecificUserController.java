package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.services.ProductDetailOfSpecificUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userproduct")
public class ProductDetailOfSpecificUserController {

    @Autowired
    ProductDetailOfSpecificUserService pdservice;

    @GetMapping("/get/{id}")
    public List<ProductDetail> getUserProduct(@PathVariable Long id)
    {
        return pdservice.getUserProduct(id);
    }

}

