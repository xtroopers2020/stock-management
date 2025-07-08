package com.stokapp.service;

import com.stokapp.entity.Product;
import com.stokapp.entity.Purchase;
import com.stokapp.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;
    

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductService productService) {
        this.purchaseRepository = purchaseRepository;
        this.productService = productService;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        // 1. Alış kaydını veritabanına kaydet
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // 2. Ürünü veritabanından tekrar çek (güncel bağlantılı halini al)
        Long productId = savedPurchase.getProduct().getId();
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı: " + productId));

        // 3. Ürün miktarını artır
        int newQuantity = product.getQuantity() + savedPurchase.getQuantity();
        product.setQuantity(newQuantity);

        // 4. Ürünü güncelle
        productService.updateProduct(product);

        return savedPurchase;
    }

    @Override
    public void deletePurchaseById(Long id) {
        purchaseRepository.deleteById(id);
    }
}
