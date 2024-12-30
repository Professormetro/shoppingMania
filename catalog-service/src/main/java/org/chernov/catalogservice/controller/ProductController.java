package org.chernov.catalogservice.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.chernov.catalogservice.config.JwtConfig;
import org.chernov.catalogservice.dto.ShopService;
import org.chernov.catalogservice.entity.Product;
import org.chernov.catalogservice.entity.Role;
import org.chernov.catalogservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final JwtConfig jwtConfig;
    private final RestTemplate restTemplate;

    @GetMapping
    public String showCatalogPage(Model model, HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            Set<Role> roles = jwtConfig.extractRolesFromToken(token);
            model.addAttribute("isSeller", roles.contains(Role.SELLER));
        }

        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("products", allProducts);
        return "catalog";
    }

    @GetMapping("/{productId}")
    public String showProductPage(@PathVariable("productId") String productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/shop-admin")
    public ResponseEntity<ShopService> showShopAdminPage(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        String shopServiceUrl = "http://localhost:8091/api/shop";

        try{
            String shopServiceResponse = restTemplate.getForObject(shopServiceUrl, String.class);
            return ResponseEntity.ok(new ShopService(token, shopServiceResponse));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ShopService(token, "Catalog-service is not available"));
        }
    }
}
