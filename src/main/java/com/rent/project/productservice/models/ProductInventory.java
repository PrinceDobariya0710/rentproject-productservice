package com.rent.project.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "product_inventory")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    //@JsonManagedReference
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDate modifiedAt;

    @Column(name = "deleted_at", nullable = false)
    private LocalDate deletedAt;

}