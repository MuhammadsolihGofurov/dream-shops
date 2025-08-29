package com.simpledev.dreamshops.service.product;

import com.simpledev.dreamshops.dto.ProductDto;
import com.simpledev.dreamshops.model.Product;
import com.simpledev.dreamshops.request.AddProductRequest;
import com.simpledev.dreamshops.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(UpdateProductRequest product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brandName);
    List<Product> getProductsByCategoryAndBrand(String category, String brandName);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brandName, String name);

    Long countProductsByBrandAndName(String brandName, String name);

    ProductDto convertToDto(Product product);

    List<ProductDto> getConvertedProducts(List<Product> products);
}
