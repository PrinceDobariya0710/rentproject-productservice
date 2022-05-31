package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService ps;


    @PostMapping("/add")
    public ResponseEntity<Product> addproduct(@RequestPart Product product,@RequestParam("file") MultipartFile file) throws IOException {
        return ps.addProduct(product,file);
    }
    @PutMapping("/edit/{Id}")
    public ResponseEntity<Product> editproduct(@PathVariable Long Id,@RequestPart Product product,@RequestParam("file") MultipartFile file) throws IOException {
        return ps.editProduct(Id,product,file);
    }

    @DeleteMapping("/delete/{Id}")
    public void deleteproduct(@PathVariable Long Id)
    {
        ps.deleteProduct(Id);
    }

    @GetMapping ("/all")
    public List<Product> getProducts() {
        return ps.getProduct();
    }
}
