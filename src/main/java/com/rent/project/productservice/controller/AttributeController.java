package com.rent.project.productservice.controller;

import com.rent.project.productservice.models.Attribute;
import com.rent.project.productservice.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {
    @Autowired
    AttributeService attributeService;

    @PostMapping("/add")
    public ResponseEntity<Attribute> addAttribute(@RequestBody Attribute attribute)
    {
        return attributeService.addAttribute(attribute);
    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<Attribute> editAttribute(@PathVariable Long Id,@RequestBody Attribute attribute){
        return attributeService.editAttribute(Id,attribute);
    }
    @DeleteMapping("/delete/{Id}")
    public void deleteAttribute(@PathVariable Long Id)
    {
        attributeService.deleteAttribute(Id);
    }
    @GetMapping ("/all")
    public List<Attribute> getAttribute() {
        return attributeService.getAttribute();
    }
}
