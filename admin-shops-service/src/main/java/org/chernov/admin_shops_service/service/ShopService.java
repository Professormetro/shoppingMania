package org.chernov.admin_shops_service.service;


import lombok.RequiredArgsConstructor;
import org.chernov.admin_shops_service.dto.CreateShopDto;
import org.chernov.admin_shops_service.dto.SellerDto;
import org.chernov.admin_shops_service.entity.AppUser;
import org.chernov.admin_shops_service.entity.Role;
import org.chernov.admin_shops_service.entity.Shop;
import org.chernov.admin_shops_service.repository.ShopRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;




    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }


    public Shop getShopById(Long shopId) {

        Optional<Shop> shop = shopRepository.findById(shopId);

        return shop.orElseThrow(() -> new RuntimeException("Shop was not found!"));
    }

    public Shop createNewShop(CreateShopDto createShopDto, SellerDto sellerDto, BindingResult bindingResult) {

        Optional<AppUser> newSeller = userService.getUserByEmail(sellerDto.getEmail());

        if(newSeller.isEmpty()) {
            bindingResult.addError(new FieldError("sellerDto", "email", "Email is already in use"));
        }

        String schemaName = createShopDto.getName().replaceAll("\\s+", "_").toLowerCase();
        String createSchemaSQL = "CREATE SCHEMA " + schemaName;

        jdbcTemplate.execute(createSchemaSQL);

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
                .owner(createShopDto.getOwner())
                .schemaName(schemaName)
                .createdAt(LocalDateTime.now())
                .build();

        setNewDataToSeller(newSeller, shop);

        return shopRepository.save(shop);
    }

    public void setNewDataToSeller(Optional<AppUser> seller, Shop shop) {

        if(seller.isPresent()) {
            seller.get().setRole(Role.SELLER);
            seller.get().setShop(shop);
        }
    }






}
