package com.ecommerce.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String name;
    private String imageUrl;
    private String description;
    private Integer stockQuantity;
    private double price;
    private double discount;
    private double specialPrice;
}
