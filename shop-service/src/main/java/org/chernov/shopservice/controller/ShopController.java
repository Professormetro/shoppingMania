package org.chernov.shopservice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/shop")
public class ShopController {

    @GetMapping
    public String showShopMainPage(Model model) {



        return "shopMain";
    }


}
