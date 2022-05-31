package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.ProductInventory;
import com.rent.project.productservice.services.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productinventory")
public class ProductInventoryController {
    @Autowired
    ProductInventoryService productInventoryService;

    @PostMapping("/add")
    public ResponseEntity<ProductInventory> addproductInventory(@RequestBody ProductInventory productInventory)
    {
        return productInventoryService.addProductInventory(productInventory);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<ProductInventory> editproductInventory(@PathVariable Long Id, @RequestBody ProductInventory productInventory)
    {
        return productInventoryService.editProductInventory(Id,productInventory);
    }

    @DeleteMapping("/delete/{Id}")
    public void deleteproductInventory(@PathVariable Long Id)
    {
        productInventoryService.deleteProductInventory(Id);
    }

    @GetMapping("/all")
    public List<ProductInventory> getProductsInventory() {
        return productInventoryService.getProductInventory();
    }
}
