package com.simpledev.dreamshops.repository;

import com.simpledev.dreamshops.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
