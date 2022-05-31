package com.rent.project.productservice.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sub_category")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subCategory_name")
    private String subCategory_name;

    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "category_id",nullable = false,referencedColumnName = "id")
    private  Category cateGory;

//    @OneToMany(mappedBy = "subcategory")
//   // @JsonBackReference
//    private Set<product> products;

    public SubCategory()
    {

    }
}
