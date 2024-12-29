package org.chernov.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopService {
    private String token;
    private String shopServiceResponse;
}
