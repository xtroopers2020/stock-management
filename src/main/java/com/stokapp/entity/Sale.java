package com.stokapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Satılan ürün, Product ile ManyToOne ilişkisi
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Satış tarihi
    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    // Satış miktarı (kaç adet satıldı)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Satış fiyatı (birim fiyat)
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;
}
