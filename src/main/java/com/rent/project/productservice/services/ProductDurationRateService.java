package com.rent.project.productservice.services;

import com.rent.project.productservice.models.ProductDurationRate;
import com.rent.project.productservice.repository.ProductDurationRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDurationRateService {
    @Autowired
    ProductDurationRateRepo durationRateRepo;

    public ResponseEntity<ProductDurationRate> addDuration(ProductDurationRate pdr)
    {
        durationRateRepo.save(pdr);
        return ResponseEntity.ok(pdr);
    }

    public ResponseEntity<ProductDurationRate> editDuration(Long id, ProductDurationRate pdr)
    {
        if(durationRateRepo.findById(id).isPresent())
        {
            pdr.setId(id);
            durationRateRepo.save(pdr);
            return ResponseEntity.ok(pdr);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteDuration(Long id){ durationRateRepo.deleteById(id);}

    public List<ProductDurationRate> getDuration() {
        return durationRateRepo.findAll();
    }
}
