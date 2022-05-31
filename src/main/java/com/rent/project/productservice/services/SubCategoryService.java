package com.rent.project.productservice.services;

import com.rent.project.productservice.models.SubCategory;
import com.rent.project.productservice.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepo scr;

    public ResponseEntity<SubCategory> addsubCategory(SubCategory sc)
    {
        scr.save(sc);
        return ResponseEntity.ok(sc);
    }

    public ResponseEntity<SubCategory> editsubCategory(Long id, SubCategory sc)
    {
        if(scr.findById(id).isPresent())
        {
            sc.setId(id);
            scr.save(sc);
            return ResponseEntity.ok(sc);
        }
        return ResponseEntity.notFound().build();
    }
    public void deletesubCategory(Long id){ scr.deleteById(id);}

    public List<SubCategory> getsubCategory() {
        return scr.findAll();
    }

}
