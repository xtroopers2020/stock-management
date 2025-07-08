package com.stokapp.service;

import com.stokapp.entity.Product;
import com.stokapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        // Güncelleme mantığı - save ile 
        return productRepository.save(product);
    }
  
    @Override
    public void deleteProductById(Long id) {
       productRepository.deleteById(id);
    }
 // ProductServiceImpl içinde
    public void increaseStock(Long productId, int amount) {
        Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı: " + productId));
        product.setQuantity(product.getQuantity() + amount);
        productRepository.save(product);
    }

} 