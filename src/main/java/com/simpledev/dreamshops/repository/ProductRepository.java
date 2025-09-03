package com.simpledev.dreamshops.repository;

import com.simpledev.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brandName);

    List<Product> findByCategoryNameAndBrand(String category, String brandName);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brandName, String name);

    Long countByBrandAndName(String brandName, String name);

    boolean existsByNameAndBrand(String productName, String productBrand);
}
