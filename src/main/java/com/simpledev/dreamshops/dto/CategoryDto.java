package com.simpledev.dreamshops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
