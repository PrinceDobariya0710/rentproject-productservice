package com.rent.project.productservice.request.format;

import com.rent.project.productservice.models.ClothingProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponseClothingProduct {

    Object UserDetails;
    Optional<ClothingProducts> clothingProducts;
}
