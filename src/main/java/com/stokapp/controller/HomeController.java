package com.stokapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/products";  // Ana sayfa isteği /products sayfasına yönlendirir
    }
}
