package com.rent.project.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ClothingProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clothingProductId;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;

    private String size;

    public ClothingProducts(Product product, String size) {
        this.product = product;
        this.size = size;
    }
}
