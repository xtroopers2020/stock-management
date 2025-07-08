package com.stokapp.controller;

import com.stokapp.entity.Product;
import com.stokapp.service.ProductService;
import com.stokapp.service.PurchaseService;
import com.stokapp.service.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final SaleService saleService;
    private final PurchaseService purchaseService;

    @Autowired
    public ProductController(ProductService productService, SaleService saleService, PurchaseService purchaseService) {
        this.productService = productService;
        this.saleService = saleService;
        this.purchaseService = purchaseService;
    }

    // Tüm ürünleri listele
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";  // templates/products.html sayfasını gösterir
    }

    // Yeni ürün formu göster
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create_product";  // templates/create_product.html
    }

    // Yeni ürünü kaydet
    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Ürün düzenleme formu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "create_product";  // templates/edit_product.html
    }

    // Ürün güncelle
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    // Ürün sil
    @GetMapping("/delete/{id}")
       public String deleteProduct(@PathVariable Long id) {
       productService.deleteProductById(id);
       return "redirect:/products";
        
    }
    
  
    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<Product> products = productService.getAllProducts();

        List<String> productNames = products.stream()
                                            .map(Product::getName)
                                            .collect(Collectors.toList());

        List<Integer> stockQuantities = products.stream()
                                                .map(Product::getQuantity)
                                                .collect(Collectors.toList());

        model.addAttribute("productNames", productNames);
        model.addAttribute("stockQuantities", stockQuantities);

        return "statistics";  // statistics.html sayfasını dönecek
    }



}
