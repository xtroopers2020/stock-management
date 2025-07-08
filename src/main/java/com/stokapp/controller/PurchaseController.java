package com.stokapp.controller;

import com.stokapp.entity.Purchase;
import com.stokapp.entity.Product;
import com.stokapp.service.ProductService;
import com.stokapp.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ProductService productService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, ProductService productService) {
        this.purchaseService = purchaseService;
        this.productService = productService;
    }

    // Alış formunu göster
    @GetMapping("/new")
    public String showPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("products", productService.getAllProducts()); // Dropdown için ürün listesi
        return "create_purchase"; // --> templates/create_purchase.html
    }

    // Alışı kaydet
    @PostMapping
    public String savePurchase(@ModelAttribute("purchase") Purchase purchase) {
        purchase.setPurchaseDate(LocalDate.now()); // Tarihi bugüne ayarla
        purchaseService.savePurchase(purchase);
        return "redirect:/products"; // Satın alma sonrası ürün listesine dön
    }
}
