package com.rent.project.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
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

    private String productDetail;

    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private SubCategory subcategory;

    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "productDurationRate_id", referencedColumnName = "id")
    private ProductDurationRate productDurationRates;

    @Column(name = "created_at", nullable = true)
    private Date createdAt;

    @Column(name = "modified_at", nullable = true)
    private Date modifiedAt;

    public Product(UserDetails userDetailsId, String productName, Integer value_duration, String product_image, String product_description, Integer deposit, Integer available_pieces, Integer product_rate, String productDetail, SubCategory subcategory, ProductDurationRate productDurationRates, Date createdAt, Date modifiedAt) {
        this.userDetailsId = userDetailsId;
        this.productName = productName;
        this.value_duration = value_duration;
        this.product_image = product_image;
        this.product_description = product_description;
        this.deposit = deposit;
        this.available_pieces = available_pieces;
        this.product_rate = product_rate;
        this.productDetail = productDetail;
        this.subcategory = subcategory;
        this.productDurationRates = productDurationRates;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
