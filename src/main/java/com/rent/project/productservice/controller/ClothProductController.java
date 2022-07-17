package com.rent.project.productservice.controller;

import com.rent.project.productservice.jwt.JwtUtil;
import com.rent.project.productservice.models.ClothingProducts;

import com.rent.project.productservice.repository.ClothingProductRepo;
import com.rent.project.productservice.request.format.ClothProduct;
import com.rent.project.productservice.request.format.UserCredObject;
import com.rent.project.productservice.services.ClothProductService;
import com.rent.project.productservice.services.userservice.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/cloth-product/")
public class ClothProductController {

    @Autowired
    ClothProductService clothProductService;

    @Autowired
    ClothingProductRepo clothingProductRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping(path = "add/cloth-product/")
    public ResponseEntity<Object> addClothProduct(@RequestHeader Map<String,String> header,@RequestBody ClothProduct clothingProduct)  {
        String token = header.get("authorization");
        Claims claims = jwtUtil.extractAllClaims(token.substring(7));
        long requestedUserId = (long)(claims.get("id",Integer.class));
        Map<Integer ,String> role_name = claims.get("role",Map.class);
        UserCredObject userCredObject = new UserCredObject(token,requestedUserId,role_name.get("role_name"));
        Long userDetailsId = userService.getUserDetailsFromUserId(userCredObject.getId(),token).getUserDetailsId();
        try{
            ClothingProducts clothingProducts = clothProductService.addClothProduct(clothingProduct);
            return ResponseEntity.ok().body(Map.of("message","Successfully created your product","data",clothingProducts));
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body(Map.of("message","Unable to add your product","data",clothingProduct));
        }
    }

    @GetMapping(path = "/get-cloth-product/")
    public ResponseEntity<Object> getClothingProduct(@RequestHeader Map<String,String> header,@RequestParam("cloth_product_id") Long cloth_product_id){
        String token = header.get("authorization");
        Claims claims = jwtUtil.extractAllClaims(token.substring(7));
        long requestedUserId = (long)(claims.get("id",Integer.class));
        Map<Integer ,String> role_name = claims.get("role",Map.class);
        UserCredObject userCredObject = new UserCredObject(token,requestedUserId,role_name.get("role_name"));
        Long userDetailsId = userService.getUserDetailsFromUserId(userCredObject.getId(),token).getUserDetailsId();

        try{
            Optional<ClothingProducts> clothingProducts = clothProductService.getClothProduct(cloth_product_id);
            if(userDetailsId!=null && (userDetailsId.equals(clothingProducts.get().getProduct().getUserDetailsId().getUserDetailsId()) || userCredObject.getRole().equals("ADMIN"))){
                return ResponseEntity.ok().body(Map.of("message","success","data",clothingProducts));
            }
            else {
                return ResponseEntity.badRequest().body(Map.of("message","unable to do request","data","bad"));
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", "Please select correct clothing product-id or exception occurred as : "+e));
        }

    }

    @GetMapping(path = "user/get-categorywise-cproduct/")
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

    @GetMapping(path = "user/get/latest-products/")
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

    @GetMapping(path = "user/get/all-cproducts/")
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
    public ResponseEntity<Object> updateSize(@RequestHeader Map<String,String> header,@RequestParam("cp_id") Long cpId,@RequestBody() String size){
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
