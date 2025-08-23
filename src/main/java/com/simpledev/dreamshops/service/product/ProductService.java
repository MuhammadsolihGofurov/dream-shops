package com.simpledev.dreamshops.service.product;

import com.simpledev.dreamshops.exceptions.ProductNotFoundException;
import com.simpledev.dreamshops.model.Category;
import com.simpledev.dreamshops.model.Product;
import com.simpledev.dreamshops.repository.CategoryRepository;
import com.simpledev.dreamshops.repository.ProductRepository;
import com.simpledev.dreamshops.request.AddProductRequest;
import com.simpledev.dreamshops.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        //  check if the category is found
        //  if yes, set it as the new product category
        //  if no, save it as a new category, then set it as a new product category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());

                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);

        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest product, Category category) {
        return new Product(
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getInventory(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ProductNotFoundException("Product not found");
                });
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());

        Category category = categoryRepository.findByName(request.getCategory().getName());

        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brandName) {
        return productRepository.findByCategoryNameAndBrand(category, brandName);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brandName, String name) {
        return productRepository.findByBrandAndName(brandName, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brandName, String name) {
        return productRepository.countByBrandAndName(brandName, name);
    }
}
