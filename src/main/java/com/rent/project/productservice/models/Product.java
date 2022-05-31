package com.rent.project.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
   // @JsonManagedReference
    @JoinColumn(name = "user_details_id", referencedColumnName = "user_details_id")
    private UserDetails userDetailsId;

    @Column(name = "product_name" )
    private String productName;

    @Column(name="value_per_duration")
    private Integer value_duration;

    @Column(name="product_image")
    private String product_image;

    @Column(name="product_description")
    private String product_description;

    @Column(name="deposit")
    private Integer deposit;

    @Column(name="available_pieces")
    private Integer available_pieces;

    @Column(name="product_rate")
    private Integer product_rate;

    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private SubCategory subcategory;

    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "productDurationRate_id", referencedColumnName = "id")
    private ProductDurationRate productDurationRates;

}
