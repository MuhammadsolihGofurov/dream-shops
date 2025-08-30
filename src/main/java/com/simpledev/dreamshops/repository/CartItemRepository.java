package com.simpledev.dreamshops.repository;

import com.simpledev.dreamshops.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
