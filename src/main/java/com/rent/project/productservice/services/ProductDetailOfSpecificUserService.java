package com.rent.project.productservice.services;

import com.rent.project.productservice.models.ProductDetail;
import com.rent.project.productservice.models.Product;
import com.rent.project.productservice.repository.ProductDetailRepo;
import com.rent.project.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailOfSpecificUserService {
    @Autowired
    ProductRepo pr;

    @Autowired
    ProductDetailRepo pdr;

    public List<ProductDetail> getUserProduct(Long id)
    {
        final int[] i = new int[1];
        List<Product> pmodel = pr.fetchUserProduct(id);

        List<ProductDetail> pdmodel = new ArrayList<>();

        i[0] =0;
        pmodel.forEach(s->
        {
            pdmodel.add(pdr.fetchProductDetail(pmodel.get(i[0]).getId()));
            i[0] = i[0] +1;
        });

        return pdmodel;
    }
}
