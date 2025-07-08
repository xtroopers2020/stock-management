package com.stokapp.controller;

import com.stokapp.service.ProfitService;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private final ProfitService profitService;

    public ReportController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GetMapping("/report")
    public String showReport(Model model) {
        double totalSales = profitService.getTotalSales();
        double totalPurchases = profitService.getTotalPurchases();
        double netProfit = profitService.getNetProfit();

        Map<String, Double> monthlySales = profitService.getMonthlySales();
        Map<String, Double> monthlyPurchases = profitService.getMonthlyPurchases();
        Map<String, Double> monthlyProfit = profitService.getMonthlyProfit();

        System.out.println("Monthly Sales: " + monthlySales);
        System.out.println("Monthly Purchases: " + monthlyPurchases);
        System.out.println("Monthly Profit: " + monthlyProfit);
        
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalPurchases", totalPurchases);
        model.addAttribute("netProfit", netProfit);
        
        model.addAttribute("monthlySales", profitService.getMonthlySales());
        model.addAttribute("monthlyPurchases", profitService.getMonthlyPurchases());
        model.addAttribute("monthlyProfit", profitService.getMonthlyProfit());
        
        return "report";
    }
    
}
