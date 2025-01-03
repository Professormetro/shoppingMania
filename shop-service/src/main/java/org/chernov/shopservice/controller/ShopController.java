package org.chernov.shopservice.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.chernov.shopservice.dto.CreateProductDto;
import org.chernov.shopservice.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public String showShopMainPage(Model model, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        //TODO


        model.addAttribute("shop", shopService.getCurrentUserShop(token));
        return "shopMain";
    }

    @GetMapping("/add-product")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new CreateProductDto());
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String addProduct(CreateProductDto createProductDto, BindingResult result){
        shopService.addProduct(createProductDto);
        return "redirect:/shop";
    }



}
