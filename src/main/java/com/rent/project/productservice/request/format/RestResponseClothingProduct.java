package com.rent.project.productservice.request.format;

import com.rent.project.productservice.models.ClothingProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponseClothingProduct {

    String response;
    Optional<ClothingProducts> clothingProducts;
}
