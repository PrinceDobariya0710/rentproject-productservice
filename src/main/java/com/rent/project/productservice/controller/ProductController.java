package com.rent.project.productservice.controller;

import com.rent.project.productservice.jwt.JwtUtil;
import com.rent.project.productservice.models.ClothingProducts;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.request.format.RestResponseClothingProduct;
import com.rent.project.productservice.request.format.UpdateAvailablePieces;
import com.rent.project.productservice.request.format.UserCredObject;
import com.rent.project.productservice.services.ProductService;
import com.rent.project.productservice.services.userservice.UserService;
import io.jsonwebtoken.Claims;
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

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    UserService userService;

    @PostMapping(path = "/add",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> addproduct(@RequestHeader Map<String,String> header,@RequestPart("product") Product product,@RequestParam("file") MultipartFile file) throws IOException {
        String token = header.get("authorization");
        Claims claims = jwtUtil.extractAllClaims(token.substring(7));
        long requestedUserId = (long)(claims.get("id",Integer.class));
        Map<Integer ,String> role_name = claims.get("role",Map.class);
        UserCredObject userCredObject = new UserCredObject(token,requestedUserId,role_name.get("role_name"));

        return ps.addProduct(product,file);
    }


    @PutMapping(path = "/update-product/")
    public ResponseEntity<Object> editProduct(@RequestHeader Map<String,String> header,@RequestParam("id")Long id,@RequestBody Product product){
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
    public ResponseEntity<Object> updateImage(@RequestHeader Map<String,String> header,@RequestParam("id")Long id,@RequestParam("file")MultipartFile file) throws IOException {
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
    public void deleteproduct(@RequestHeader Map<String,String> header,@PathVariable Long Id)
    {
        ps.deleteProduct(Id);
    }

    @GetMapping ("/all")
    public List<Product> getProducts() {
        return ps.getProduct();
    }

    @GetMapping("/product_by_product_id/")
    public RestResponseClothingProduct getProductFromProductId(@RequestHeader Map<String,String> header,@RequestParam("product_id") String product_id) {
        String token = header.get("authorization");
        try {
            Optional<ClothingProducts> clothingProducts = ps.getProductByProductId(Long.parseLong(product_id));
            Long userDetailId = clothingProducts.get().getProduct().getUserDetailsId().getUserDetailsId();
            Object userDetails = userService.getAddressOfUser(userDetailId,token);
            return new RestResponseClothingProduct(userDetails, clothingProducts);
        } catch (Exception e) {
            return new RestResponseClothingProduct(e.toString(), null);
        }
    }
    @PutMapping("/update_pieces/")
    public ResponseEntity<Object> updateAvailablePiecesOfProduct(@RequestHeader Map<String,String> header,@RequestBody UpdateAvailablePieces updateAvailablePieces){
        String response = ps.updateAvailablePiecesNumber(updateAvailablePieces);
        if (response.equals("success")){
            return ResponseEntity.ok().body(Map.of(
                    "response","success",
                    "data",updateAvailablePieces
            ));
        }
        else {
            return ResponseEntity.badRequest().body(Map.of(
                    "response", "fail",
                    "reason", response));
        }

    }

    @GetMapping("/get/product/by/")
    public ResponseEntity<Object> getProductByProductId(@RequestParam("product_id") String productId){
        try {
            Optional<ClothingProducts> clothingProducts = ps.getProductByProductId(Long.parseLong(productId));
            return ResponseEntity.ok().body(Map.of("message","success","data",clothingProducts));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("message","unable to get product","data",null));
        }

    }



}
