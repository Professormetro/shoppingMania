package org.chernov.admin_shops_service.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chernov.admin_shops_service.dto.CreateShopDto;
import org.chernov.admin_shops_service.entity.Shop;
import org.chernov.admin_shops_service.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public String showAllShops(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "allShops";
    }

    @GetMapping("/{shopId}")
    public String showShop(@PathVariable("shopId") Long shopId, Model model) {
        Shop shop = shopService.getShopById(shopId);
        model.addAttribute("shop", shop);
        return "shopPage";
    }

    @GetMapping("/create-shop")
    public String showFormToCreateNewShop(Model model) {
        model.addAttribute("createShopDto", new CreateShopDto());
        model.addAttribute("success", false);
        return "createShop";
    }


    @PostMapping("/create-shop")
    public String createNewShop(Model model, @ModelAttribute @Valid CreateShopDto createShopDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "createShop";
        }

        Shop createdShop = shopService.createNewShop(createShopDto, bindingResult);

        model.addAttribute("createdShop", createdShop);
        model.addAttribute("success", true);

        return "redirect:/api/shops";
    }

}
