package org.chernov.admin_shops_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shops")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name")
    private String name;

    @Column(name = "shop_logo")
    private String logoImageUrl;

    @Column(name = "shop_rating")
    private int rating;

    @OneToOne
    @JoinColumn(name = "owner_id", unique = true)
    private AppUser owner;

    @Column(name = "products_on_sale")
    private Long productsOnSale;

    @Column(name = "products_in_stock")
    private Long productsInStock;

    private String schemaName;

}
