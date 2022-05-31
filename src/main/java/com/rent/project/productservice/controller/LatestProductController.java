package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.services.LatestProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/latestproduct")
public class LatestProductController {
    @Autowired
    LatestProductService lps;

    @GetMapping("/get")
    public List<ProductDetail> getLatestProduct()
    {
        return lps.getLatestProduct();
    }
}
