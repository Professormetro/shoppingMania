package org.chernov.shopservice.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDto {

    private String name;
    private int quantity;
    private String description;
    private String category;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
    private String imageUrl;
}
