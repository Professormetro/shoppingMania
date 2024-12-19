package org.chernov.admin_shops_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private String name;

    private String description;

    private String category;

    private int rating;

    private Review review;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;

    private int stock;

    private String imageUrl;

    private LocalDateTime createdAt;

}
