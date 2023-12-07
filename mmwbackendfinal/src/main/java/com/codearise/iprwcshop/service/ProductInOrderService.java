package com.codearise.iprwcshop.service;

import com.codearise.iprwcshop.entity.ProductInOrder;
import com.codearise.iprwcshop.entity.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder getProductInOrder(String itemId, User user);
}
