package com.rent.project.productservice.services;

import com.rent.project.productservice.models.Attribute;
import com.rent.project.productservice.repository.AttributeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttributeService {
    @Autowired
    AttributeRepo attributeRepo;

    public ResponseEntity<Attribute> addAttribute(Attribute attribute)
    {
        attributeRepo.save(attribute);
        return ResponseEntity.ok(attribute);
    }

    public ResponseEntity<Attribute> editAttribute(Long id,Attribute attribute)
    {
        if(attributeRepo.findById(id).isPresent())
        {
            attribute.setId(id);
            attributeRepo.save(attribute);
            return ResponseEntity.ok(attribute);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteAttribute(Long id){ attributeRepo.deleteById(id);}

    public List<Attribute> getAttribute() {
        return attributeRepo.findAll();
    }
}
