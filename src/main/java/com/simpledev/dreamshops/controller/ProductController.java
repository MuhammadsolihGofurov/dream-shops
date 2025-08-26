package com.simpledev.dreamshops.controller;

import com.simpledev.dreamshops.exceptions.ProductNotFoundException;
import com.simpledev.dreamshops.exceptions.ResourceNotFoundException;
import com.simpledev.dreamshops.model.Product;
import com.simpledev.dreamshops.request.AddProductRequest;
import com.simpledev.dreamshops.request.UpdateProductRequest;
import com.simpledev.dreamshops.response.ApiResponse;
import com.simpledev.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Found", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", e.getMessage()));
        }
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Found", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request, @PathVariable Long id) {
        try {
            Product theProduct = productService.addProduct(request);
            return ResponseEntity.ok(new ApiResponse("Create", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long id) {
        try {
            Product theProduct = productService.updateProduct(request, id);
            return ResponseEntity.ok(new ApiResponse("Update", theProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Deleted", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByName(name);

            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not Found", null));
            }

            return ResponseEntity.ok(new ApiResponse("Found by Product name", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-brand/{brand}")
    public ResponseEntity<ApiResponse> getProductByBrand(@PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);

            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not Found", null));
            }

            return ResponseEntity.ok(new ApiResponse("Found by Product brand", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-category-name/{categoryName}")
    public ResponseEntity<ApiResponse> getProductByCategoryName(@PathVariable String categoryName) {
        try {
            List<Product> products = productService.getProductsByCategory(categoryName);

            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not Found", null));
            }

            return ResponseEntity.ok(new ApiResponse("Found by Product category name", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-category-name-and-brand/{categoryName}/{brand}")
    public ResponseEntity<ApiResponse> getProductByCategoryNameAndBrand(@PathVariable String categoryName, @PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(categoryName, brand);

            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not Found", null));
            }

            return ResponseEntity.ok(new ApiResponse("Found by Product category name and brand", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-brand-and-name/{brand}/{name}")
    public ResponseEntity<ApiResponse> getProductsByNameAndBrand(@PathVariable String name, @PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);

            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not Found", null));
            }

            return ResponseEntity.ok(new ApiResponse("Found by Product name and brand", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
