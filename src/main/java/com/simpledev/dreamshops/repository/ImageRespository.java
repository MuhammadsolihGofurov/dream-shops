package com.simpledev.dreamshops.repository;

import com.simpledev.dreamshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRespository extends JpaRepository<Image, Long> {
}
