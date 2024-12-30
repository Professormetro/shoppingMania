package org.chernov.catalogservice.controller;


import lombok.RequiredArgsConstructor;
import org.chernov.catalogservice.entity.Product;
import org.chernov.catalogservice.service.BucketService;
import org.chernov.catalogservice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;
    private final ProductService productService;

    @GetMapping
    public String getBucket(Model model) {
        List<Long> bucketItemsIds = bucketService.getBucketItems();
        List<Product> bucketItems;

        for (Long bucketItemId : bucketItemsIds) {
            bucketItems.add(productService.getProductById(bucketItemId));
        }

        model.addAttribute("bucketItems", bucketItems);
        return "bucket";
    }

    @PostMapping("/add/{productId}")
    public void addToBucket(@PathVariable Long productId) {
        bucketService.addProductToBucket(productId);
    }

    @DeleteMapping("/remove/{productId}")
    public String deleteProductFromBucket(@PathVariable Long productId) {
        bucketService.removeProductFromBucket(productId);
        return "bucket";
    }

    @DeleteMapping("/clear")
    public String clearBucket() {
        bucketService.clearBucket();
        return "bucket";
    }



}
