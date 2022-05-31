package com.rent.project.productservice.models;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String category_name;

   /* @OneToMany(mappedBy = "cateGory")
    @JsonBackReference
    private Set<subCategory> subCategories;*/

}
