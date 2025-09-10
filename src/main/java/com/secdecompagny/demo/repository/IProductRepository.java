package com.secdecompagny.demo.repository;

import com.secdecompagny.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY p.id")
    List<Product> findAllByOrderByIdAsc();

    @Query("SELECT COALESCE(MAX(p.id), 0) + 1 FROM Product p")
    Long getNextId();
}
