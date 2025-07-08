package com.stokapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Alınan ürün
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Alış tarihi
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    // Alınan miktar
    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Alış fiyatı (birim fiyat)
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;
}
