package org.chernov.shopservice.service;


import lombok.RequiredArgsConstructor;
import org.chernov.shopservice.dto.CreateProductDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {


    public void addProduct(CreateProductDto createProductDto, Long shopId) {

    }



    public Object getCurrentUserShop(String token) {
    }
}
