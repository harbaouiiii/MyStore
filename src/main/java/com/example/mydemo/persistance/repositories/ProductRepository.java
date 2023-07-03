package com.example.mydemo.persistance.repositories;

import com.example.mydemo.persistance.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory_Name(String name);
    List<Product> findByNameContains(String name);
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByQuantityEquals(Integer qte);
}
