package com.stokapp.repository;
import java.util.List;

import com.stokapp.entity.Sale;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository <Sale,Long>{
	List<Sale> findByProductIdAndSaleDateAfter(Long productId, LocalDate date);
	@Query("SELECT SUM(s.quantity) FROM Sale s WHERE s.product.id = :productId AND s.saleDate >= :startDate")
    Integer sumQuantityByProductIdAndDateAfter(@Param("productId") Long productId, @Param("startDate") LocalDate startDate);

    @Query("SELECT SUM(s.quantity) FROM Sale s WHERE s.product.category = :category AND s.saleDate >= :startDate")
    Integer sumQuantityByCategoryAndDateAfter(@Param("category") String category, @Param("startDate") LocalDate startDate);
    

}


