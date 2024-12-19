package org.chernov.admin_shops_service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "reviews")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private String id;

    private String productName;

    private String authorUsername;

    private int rating;

    private String reviewText;

    private LocalDateTime reviewDate;


}
