package org.chernov.catalogservice.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Document(collection = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    //TODO настроить связь с таблицей магазинов
    private Long shopId;

    private String shopName;

    private List<Review> reviews;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;

    private String imageUrl;

    private List<Feature> features;

}
