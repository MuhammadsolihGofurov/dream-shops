package com.simpledev.dreamshops.service.order;

import com.simpledev.dreamshops.dto.OrderDto;
import com.simpledev.dreamshops.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);


    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
