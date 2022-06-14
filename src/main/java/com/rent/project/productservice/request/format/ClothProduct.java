package com.rent.project.productservice.request.format;

import com.rent.project.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothProduct extends Product implements Serializable {

    private String size;

}
