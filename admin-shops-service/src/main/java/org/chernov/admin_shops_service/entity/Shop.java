package org.chernov.admin_shops_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name")
    private String name;

    @Column(name = "shop_logo", columnDefinition = "TEXT")
    private String logoImageUrl;

    @Column(name = "shop_rating")
    private int rating;

    @Column(name = "user_owner_id")
   private Long ownerId;

    @Column(name = "products_on_sale")
    private Long productsOnSale;

    @Column(name = "products_in_stock")
    private Long productsInStock;

    @Column(name = "schema_name")
    private String schemaName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
