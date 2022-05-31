package com.rent.project.productservice.repository;

import com.rent.project.productservice.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepo extends JpaRepository<Attribute,Long> {
}
