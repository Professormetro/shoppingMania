package org.chernov.admin_shops_service.controller;


import lombok.RequiredArgsConstructor;
import org.chernov.admin_shops_service.entity.Shop;
import org.chernov.admin_shops_service.repository.ShopRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public String showAllShops(Model model) {
        model.addAttribute("shops", shopRepository.findAll());
        return "allShops";
    }

    @GetMapping("/{shopId}")
    public String showShop(@PathVariable("shopId") Long shopId, Model model) {

        Shop shop = shopService.getShopById();
        model.addAttribute("shop", shop);

        return "shopPage";
    }


    @PostMapping("/createShop")
    public String createNewShop(CreateShopDto createShopDto) {

        Shop createdShop = shopService.createNewShop(createShopDto);

        return "redirect:/shops/{shopId}";
    }





}
