package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.SubCategory;
import com.rent.project.productservice.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping("/add")
    public ResponseEntity<SubCategory> addsubcategory(@RequestBody SubCategory sb)
    {
        return subCategoryService.addsubCategory(sb);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<SubCategory> editsubcategory(@PathVariable Long Id, @RequestBody SubCategory sb){
        return subCategoryService.editsubCategory(Id,sb);
    }
    @DeleteMapping("/delete/{Id}")
    public void deletesubcategory(@PathVariable Long Id)
    {
        subCategoryService.deletesubCategory(Id);
    }
    @GetMapping ("/all")
    public List<SubCategory> getsubcategories() {
        return subCategoryService.getsubCategory();
    }
}
