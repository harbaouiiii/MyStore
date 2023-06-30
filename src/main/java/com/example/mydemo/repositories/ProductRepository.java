package com.example.mydemo.repositories;

import com.example.mydemo.entities.Category;
import com.example.mydemo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<List<Product>> findByCategory_Name(String name);
    Optional<List<Product>> findByNameContains(String name);
    Optional<List<Product>> findByPriceBetween(Double min, Double max);
    Optional<List<Product>> findByQuantityEquals(Integer qte);
}
