package com.rent.project.productservice.services;

import com.rent.project.productservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    com.rent.project.productservice.repository.CategoryRepo CategoryRepo;

    public ResponseEntity<Category> addCategory(Category ct)
    {
        CategoryRepo.save(ct);
        return ResponseEntity.ok(ct);
    }

    public ResponseEntity<Category> editCategory(Long id, Category ct)
    {
        if(CategoryRepo.findById(id).isPresent())
        {
            ct.setId(id);
            CategoryRepo.save(ct);
            return ResponseEntity.ok(ct);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteCategory(Long id){ CategoryRepo.deleteById(id);}

    public List<Category> getCategory() {
        return CategoryRepo.findAll();
    }

}
