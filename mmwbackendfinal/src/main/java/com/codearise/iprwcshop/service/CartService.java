package com.codearise.iprwcshop.service;

import com.codearise.iprwcshop.entity.Cart;
import com.codearise.iprwcshop.entity.ProductInOrder;
import com.codearise.iprwcshop.entity.User;
import java.util.Collection;

public interface CartService {
    Cart getCart(User user);
    void linkUserWithCart(Collection<ProductInOrder> productInOrders, User user);
    void deleteItem(String itemId, User user);
    void checkoutCurrentCart(User user);
}
