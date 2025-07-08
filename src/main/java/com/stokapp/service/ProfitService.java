package com.stokapp.service;

import com.stokapp.entity.Sale;
import com.stokapp.entity.Purchase;
import com.stokapp.repository.SaleRepository;
import com.stokapp.repository.PurchaseRepository;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service
public class ProfitService {

    private final SaleRepository saleRepository;
    private final PurchaseRepository purchaseRepository;

    public ProfitService(SaleRepository saleRepository, PurchaseRepository purchaseRepository) {
        this.saleRepository = saleRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public double getTotalSales() {
        return saleRepository.findAll().stream()
                .mapToDouble(sale -> sale.getQuantity() * sale.getUnitPrice())
                .sum();
    }

    public double getTotalPurchases() {
        return purchaseRepository.findAll().stream()
                .mapToDouble(purchase -> purchase.getQuantity() * purchase.getUnitPrice())
                .sum();
    }

    public double getNetProfit() {
        return getTotalSales() - getTotalPurchases();
    }
    public Map<String, Double> getMonthlySales() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Map<String, Double> salesMap = new LinkedHashMap<>();

        for (Sale sale : saleRepository.findAll()) {
            String month = sale.getSaleDate().format(formatter);
            double amount = sale.getQuantity() * sale.getUnitPrice();
            salesMap.put(month, salesMap.getOrDefault(month, 0.0) + amount);
        }

        return salesMap;
    }

    public Map<String, Double> getMonthlyPurchases() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Map<String, Double> purchaseMap = new LinkedHashMap<>();

        for (Purchase purchase : purchaseRepository.findAll()) {
            String month = purchase.getPurchaseDate().format(formatter);
            double amount = purchase.getQuantity() * purchase.getUnitPrice();
            purchaseMap.put(month, purchaseMap.getOrDefault(month, 0.0) + amount);
        }

        return purchaseMap;
    }

    public Map<String, Double> getMonthlyProfit() {
        Map<String, Double> sales = getMonthlySales();
        Map<String, Double> purchases = getMonthlyPurchases();
        Map<String, Double> profitMap = new LinkedHashMap<>();

        // Tüm ayları bir kümede toplama (sales ve purchases içinde olan)
        Set<String> allMonths = new TreeSet<>();
        allMonths.addAll(sales.keySet());
        allMonths.addAll(purchases.keySet());

        for (String month : allMonths) {
            double sale = sales.getOrDefault(month, 0.0);
            double purchase = purchases.getOrDefault(month, 0.0);
            profitMap.put(month, sale - purchase);
        }

        return profitMap;
    }


}
