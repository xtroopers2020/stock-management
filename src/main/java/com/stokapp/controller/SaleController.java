package com.stokapp.controller;

import com.stokapp.entity.Sale;
import com.stokapp.entity.Product;
import com.stokapp.service.SaleService;
import com.stokapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;

    @Autowired
    public SaleController(SaleService saleService, ProductService productService) {
        this.saleService = saleService;
        this.productService = productService;
    }

    // Satış listesi
    @GetMapping
    public String listSales(Model model) {
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales);
        return "sales";
    }

    // Yeni satış formu
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("products", productService.getAllProducts());
        return "create_sale";
    }

    // Satış kaydet
    @PostMapping
    public String saveSale(@ModelAttribute("sale") Sale sale, Model model) {
        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDate.now());
        }

        Product product = productService.getProductById(sale.getProduct().getId())
                              .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        // Stok kontrolü - stok yeterli değilse hata ver
        if (sale.getQuantity() > product.getQuantity()) {
            model.addAttribute("sale", sale);
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("stockError", "Yeterli stok yok! Mevcut miktar: " + product.getQuantity());
            return "create_sale"; // Hatalıysa formu tekrar göster
        }

        // Stok yeterli, satış kaydet
        sale.setProduct(product);
        saleService.saveSale(sale);

        // Stok güncelle
        product.setQuantity(product.getQuantity() - sale.getQuantity());
        productService.updateProduct(product);

        // Son 3 ay ortalama satış bilgisi (ürün ve kategori bazında)
        double avgSales = saleService.getAverageMonthlySales(product.getId(), 3);
        double avgCategorySales = saleService.getAverageMonthlySalesByCategory(product.getCategory(), 3);

        // Bilgilendirici mesaj oluştur
        String infoMessage = "Satış başarılı!<br>" +
                             "Son 3 ayda bu üründen ortalama aylık satılan miktar: " + String.format("%.2f", avgSales) + ".<br>" +
                             "Kategori genelinde ortalama satış: " + String.format("%.2f", avgCategorySales) + ".<br>";

        if (avgSales > 50) {
            infoMessage += "<strong>Bu ürün çok satılıyor, stoklarınızı artırmanızı öneririz!</strong>";
        } else if (product.getQuantity() < 10) {
            infoMessage += "<strong>Stoklarınız azalmaya başladı, dikkat!</strong>";
        } else {
            infoMessage += "Stok durumu şu an iyi görünüyor.";
        }

        model.addAttribute("infoMessage", infoMessage);
        model.addAttribute("sale", new Sale());
        model.addAttribute("products", productService.getAllProducts());

        return "create_sale";  // Satış sonrası tekrar satış formuna dönerken mesaj göster
    }


    // Satış sil
    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        saleService.deleteSaleById(id);
        return "redirect:/sales";
    }
}
