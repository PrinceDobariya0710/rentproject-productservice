package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.ClothingProducts;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.request.format.ClothProduct;
import com.rent.project.productservice.services.ClothProductService;
import com.rent.project.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService ps;

    @PostMapping(path = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> addproduct(@RequestPart("product") Product product,@RequestParam("file") MultipartFile file) throws IOException {
        return ps.addProduct(product,file);
    }


    @PutMapping(path = "/update-product/")
    public ResponseEntity<Object> editProduct(@RequestParam("id")Long id,@RequestBody Product product){
        try {
            Optional<Product> updatedProduct = ps.update(id, product);
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "success",
                    "reason", updatedProduct));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "we can not update your product. Please try again with correct data"));
        }

    }


    @PatchMapping(path = "/update-image/")
    public ResponseEntity<Object> updateImage(@RequestParam("id")Long id,@RequestParam("file")MultipartFile file) throws IOException {
        try{
            ps.updateImage(id,file);
            return ResponseEntity.ok().body(Map.of(
                    "response", "success",
                    "reason", "Your image is updated successfully."));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "we are not able to update your iamge.Please try again"));
        }
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
