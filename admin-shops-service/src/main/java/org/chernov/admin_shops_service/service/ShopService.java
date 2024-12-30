package org.chernov.admin_shops_service.service;


import lombok.RequiredArgsConstructor;
import org.chernov.admin_shops_service.dto.CreateShopDto;
import org.chernov.admin_shops_service.entity.AppUser;
import org.chernov.admin_shops_service.entity.Role;
import org.chernov.admin_shops_service.entity.Shop;
import org.chernov.admin_shops_service.repository.ShopRepository;
import org.chernov.admin_shops_service.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;


    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }


    public Shop getShopById(Long shopId) {

        Optional<Shop> shop = shopRepository.findById(shopId);

        return shop.orElseThrow(() -> new RuntimeException("Shop was not found!"));
    }

    public Shop createNewShop(CreateShopDto createShopDto, BindingResult bindingResult) {

        Optional<AppUser> newSeller = userService.getUserByEmail(createShopDto.getSellerEmail());

        if(newSeller.isEmpty()) {
            bindingResult.addError(new FieldError("sellerDto", "email", "Email is already in use"));
        }

        AppUser seller = newSeller.get();

        String schemaName = createShopDto.getName().replaceAll("\\s+", "_").toLowerCase();
        String createSchemaSQL = "CREATE SCHEMA " + schemaName;

        jdbcTemplate.execute(createSchemaSQL);

        //TODO поменять колонки таблицы Products, сделать связь с Reviews и Features

        String createProductTableSQL = "CREATE TABLE " + schemaName + ".products (" +
                "id SERIAL PRIMARY KEY," +
                "article BIGINT NOT NULL UNIQUE," +
                "name VARCHAR(255) NOT NULL," +
                "category VARCHAR(255)," +
                "rating INT DEFAULT 0," +
                "description TEXT," +
                "price DECIMAL(10,2) NOT NULL," +
                "price_with_discount DECIMAL(10,2)," +
                "stock INT NOT NULL," +
                "image_url VARCHAR(255)," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        jdbcTemplate.execute(createProductTableSQL);

        Shop shop = Shop.builder()
                .name(createShopDto.getName())
                .logoImageUrl(createShopDto.getLogoImageUrl())
                .ownerId(seller.getId())
                .productsInStock(0L)
                .rating(0)
                .productsOnSale(0L)
                .schemaName(schemaName)
                .createdAt(LocalDateTime.now())
                .build();

        setNewDataToSeller(seller);

        return shopRepository.save(shop);
    }

    public void setNewDataToSeller(AppUser seller) {
        seller.setRoles(Set.of(Role.SELLER));
        userRepository.save(seller);
    }
}
