package com.rent.project.productservice.services;

import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.repository.ProductDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductDetailService {
    @Autowired
    ProductDetailRepo proDR;
    public ResponseEntity<ProductDetail> addProductDetail(ProductDetail productDetail)
    {
        proDR.save(productDetail);
        return ResponseEntity.ok(productDetail);
    }

    public ResponseEntity<ProductDetail> editProductDetail(Long id, ProductDetail productDetail)
    {
       /*productrepo.findById(id).map(p1->
       {
           System.out.println("hello");
           Product.setId(id);
           productrepo.save(Product);
           return ResponseEntity.ok("updated..");
       });*/
        if(proDR.findById(id).isPresent())
        {
            productDetail.setId(id);
            proDR.save(productDetail);
            return ResponseEntity.ok(productDetail);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteProductDetail(Long id){ proDR.deleteById(id);}
    public List<ProductDetail> getProductDetail() {
        return proDR.findAll();
    }
}
