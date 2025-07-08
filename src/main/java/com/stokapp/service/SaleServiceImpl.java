package com.stokapp.service;

import com.stokapp.entity.Sale;
import com.stokapp.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }

    // Ortalama aylık satış miktarı hesaplayan metot
    public double getAverageMonthlySales(Long productId, int months) {
        
        LocalDate startDate = LocalDate.now().minusMonths(months);
        Integer totalSales = saleRepository.sumQuantityByProductIdAndDateAfter(productId, startDate);
        if (totalSales == null) return 0;
        return totalSales / (double) months;
    }
    @Override
    public double getAverageMonthlySalesByCategory(String category, int months) {
        LocalDate startDate = LocalDate.now().minusMonths(months);
        Integer totalSales = saleRepository.sumQuantityByCategoryAndDateAfter(category, startDate);
        if (totalSales == null) return 0;
        return totalSales / (double) months;
    }

}


 