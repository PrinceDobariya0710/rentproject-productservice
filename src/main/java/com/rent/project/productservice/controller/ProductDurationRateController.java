package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.ProductDurationRate;
import com.rent.project.productservice.services.ProductDurationRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duration")
public class ProductDurationRateController {
    @Autowired
    ProductDurationRateService productDurationRateService;

    @PostMapping("/add")
    public ResponseEntity<ProductDurationRate> addDuration(@RequestBody ProductDurationRate productDurationRate)
    {
        return productDurationRateService.addDuration(productDurationRate);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<ProductDurationRate> editDuration(@PathVariable Long Id, @RequestBody ProductDurationRate productDurationRate){
        return productDurationRateService.editDuration(Id,productDurationRate);
    }
    @DeleteMapping("/delete/{Id}")
    public void deleteDuration(@PathVariable Long Id)
    {
        productDurationRateService.deleteDuration(Id);
    }
    @GetMapping ("/all")
    public List<ProductDurationRate> getDuration() {
        return productDurationRateService.getDuration();
    }
}
