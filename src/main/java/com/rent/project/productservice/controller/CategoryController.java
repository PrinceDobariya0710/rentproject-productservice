package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.Category;
import com.rent.project.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService cs;

    @PostMapping("/add")
    public ResponseEntity<Category> addcategory(@RequestBody Category cg)
    {
        return cs.addCategory(cg);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<Category> editcategory(@PathVariable Long Id, @RequestBody Category cg){
        return cs.editCategory(Id,cg);
    }
    @DeleteMapping("/delete/{Id}")
    public void deletecategory(@PathVariable Long Id)
    {
        cs.deleteCategory(Id);
    }
    @GetMapping ("/all")
    public List<Category> getcategories() {
        return cs.getCategory();
    }
}
