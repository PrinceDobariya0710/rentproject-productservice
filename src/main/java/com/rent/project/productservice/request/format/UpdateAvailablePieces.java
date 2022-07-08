package com.rent.project.productservice.request.format;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAvailablePieces {
    Integer availablePieces;
    Long productId;
}
