package com.codearise.iprwcshop.service;

import com.codearise.iprwcshop.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderMain> getAllOrders(Pageable pageable);
    Page<OrderMain> getOrderByStatus(Integer status, Pageable pageable);
    Page<OrderMain> getUserOrder(String email, Pageable pageable);
    Page<OrderMain> getPhoneOrder(String phone, Pageable pageable);
    OrderMain getOrder(Long orderId);
    OrderMain finishOrder(Long orderId);
    OrderMain cancelOrder(Long orderId);
}
