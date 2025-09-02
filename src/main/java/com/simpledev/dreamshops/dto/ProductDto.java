package com.simpledev.dreamshops.dto;

import com.simpledev.dreamshops.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;

    private CategoryDto category;
    private List<ImageDto> images;
}
