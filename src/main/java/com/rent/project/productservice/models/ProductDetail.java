package com.rent.project.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productd_id", nullable = false)
    private Long id;

    @Column(name = "attribute_value", nullable = false, length = 30)
    private String attributeValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JsonManagedReference
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JsonManagedReference
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;


}