package com.rent.project.productservice.services;

import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.models.ProductInventory;
import com.rent.project.productservice.repository.ProductInventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInventoryService {
    @Autowired
    ProductInventoryRepo productInventoryRepo;

    public ResponseEntity<ProductInventory> addProductInventory(ProductInventory productInventory)
    {
        productInventoryRepo.save(productInventory);
        return ResponseEntity.ok(productInventory);
    }

    public ResponseEntity<ProductInventory> editProductInventory(Long id, ProductInventory productInventory)
    {
        if(productInventoryRepo.findById(id).isPresent())
        {
            productInventory.setId(id);
            productInventoryRepo.save(productInventory);
            return ResponseEntity.ok(productInventory);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteProductInventory(Long id){ productInventoryRepo.deleteById(id);}
    public List<ProductInventory> getProductInventory() {
        return productInventoryRepo.findAll();
    }
}
