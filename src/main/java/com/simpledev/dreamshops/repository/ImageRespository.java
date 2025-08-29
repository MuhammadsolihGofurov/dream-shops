package com.simpledev.dreamshops.repository;

import com.simpledev.dreamshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRespository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
