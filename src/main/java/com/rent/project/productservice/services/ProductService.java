package com.rent.project.productservice.services;

import com.rent.project.productservice.models.ClothingProducts;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.repository.CategoryRepo;
import com.rent.project.productservice.repository.ClothingProductRepo;
import com.rent.project.productservice.repository.ProductRepo;
import com.rent.project.productservice.repository.SubCategoryRepo;
import com.rent.project.productservice.request.format.ClothProduct;
import com.rent.project.productservice.request.format.UpdateAvailablePieces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productrepo;

    @Autowired
    ClothingProductRepo clothingProductRepo;

    @Autowired
    FileUploadService fileUploadService;

    public ResponseEntity<Product> addProduct(Product product,MultipartFile file) throws IOException {
        String filename=fileUploadService.uploadFile(file);
        product.setProduct_image(filename);
        productrepo.save(product);
        return ResponseEntity.ok(product);

    }


    //delete particular product
    public void deleteProduct(Long id){
        productrepo.deleteById(id);

    }

    public List<Product> getProduct() {
        return productrepo.findAll();
    }

    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Date modifiedAt = Date.valueOf(LocalDate.now());
        return productrepo.findById(id).map(target -> {
                        target.setProductName(product.getProductName());
                        target.setProduct_rate(product.getProduct_rate());
                        target.setUserDetailsId(product.getUserDetailsId());
                        target.setDeposit(product.getDeposit());
                        target.setAvailable_pieces(product.getAvailable_pieces());
                        target.setProduct_description(product.getProduct_description());
                        target.setProductDetail(product.getProductDetail());
                        target.setValue_duration(product.getValue_duration());
                        target.setSubcategory(product.getSubcategory());
                        target.setModifiedAt(modifiedAt);
                        target.setProductDurationRates(product.getProductDurationRates());
                        target.setProductName(product.getProductName());
                        return target;
            });
    }

    @Transactional
    public void updateImage(Long id, MultipartFile file) throws IOException {
        String filename=fileUploadService.uploadFile(file);
        productrepo.updateProductImage(id,filename);
    }

    @Transactional
    public String updateAvailablePiecesNumber(UpdateAvailablePieces updateAvailablePieces){
        try{
            System.out.println(updateAvailablePieces);
            productrepo.updateAvailablePieces(updateAvailablePieces.getProductId(), updateAvailablePieces.getAvailablePieces());
            return "success";
        }
        catch (Exception e){
            return e.getMessage();
        }


    }

    public Optional<ClothingProducts> getProductByProductId(Long product_id){

        Optional<ClothingProducts> clothingProducts = clothingProductRepo.findByProductId(product_id);
        return clothingProducts;
//        Optional<Product> product = productrepo.findById(product_id);
    }
}
