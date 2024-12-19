package org.chernov.admin_shops_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    private Shop shop;

    private Long article;

    private String name;

    private String description;

    private String category;

    private int rating;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;

    private int stock;

    private String imageUrl;

    private LocalDateTime createdAt;

}
