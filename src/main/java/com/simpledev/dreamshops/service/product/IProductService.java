package com.simpledev.dreamshops.service.product;

import com.simpledev.dreamshops.model.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    void updateProduct(Product product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brandName);
    List<Product> getProductsByCategoryAndBrand(String category, String brandName);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brandName, String name);

    Long countProductsByBrandAndName(String brandName, String name);
}
