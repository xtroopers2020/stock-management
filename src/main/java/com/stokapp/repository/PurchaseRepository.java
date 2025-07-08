package com.stokapp.repository;

import com.stokapp.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT SUM(p.quantity) FROM Purchase p WHERE p.product.id = :productId")
    Integer sumQuantityByProductId(@Param("productId") Long productId);
    

}
