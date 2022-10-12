package com.learn.cartapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @GetMapping("/cart")
    public String addToCart() {
        return "Added item to cart";
    }
}
