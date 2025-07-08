package com.stokapp.service;

import com.stokapp.entity.Sale;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SaleService {
    List<Sale> getAllSales();
    Optional<Sale> getSaleById(Long id);
    Sale saveSale(Sale sale);
    void deleteSaleById(Long id);

    // Ortalama aylık satış tahmini metodu
    double getAverageMonthlySales(Long productId, int months);
    double getAverageMonthlySalesByCategory(String category, int months);
    

}   


