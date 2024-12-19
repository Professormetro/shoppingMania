package org.chernov.catalogservice.controller;


import lombok.RequiredArgsConstructor;
import org.chernov.catalogservice.entity.Product;
import org.chernov.catalogservice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String showCatalogPage(Model model) {
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




}
