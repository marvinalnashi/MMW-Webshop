package com.codearise.iprwcshop.service.impl;

import com.codearise.iprwcshop.entity.Cart;
import com.codearise.iprwcshop.entity.OrderMain;
import com.codearise.iprwcshop.entity.ProductInOrder;
import com.codearise.iprwcshop.entity.User;
import com.codearise.iprwcshop.repository.CartRepository;
import com.codearise.iprwcshop.repository.OrderRepository;
import com.codearise.iprwcshop.repository.ProductInOrderRepository;
import com.codearise.iprwcshop.repository.UserRepository;
import com.codearise.iprwcshop.service.CartService;
import com.codearise.iprwcshop.service.ProductService;
import com.codearise.iprwcshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void linkUserWithCart(Collection<ProductInOrder> productInOrders, User user) {
        Cart userCart = user.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = userCart.getProducts();
            Optional<ProductInOrder> orderedProductNotLogin = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder orderedProduct;
            if (orderedProductNotLogin.isPresent()) {
                orderedProduct = orderedProductNotLogin.get();
                orderedProduct.setCount(productInOrder.getCount() + orderedProduct.getCount());
            } else {
                orderedProduct = productInOrder;
                orderedProduct.setCart(userCart);
                userCart.getProducts().add(orderedProduct);
            }
            productInOrderRepository.save(orderedProduct);
        });
        cartRepository.save(userCart);
    }

    @Override
    @Transactional
    public void deleteItem(String itemId, User user) {
        Optional<ProductInOrder> orderedProduct = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        orderedProduct.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    @Transactional
    public void checkoutCurrentCart(User user) {
        OrderMain currentOrder = new OrderMain(user);
        orderRepository.save(currentOrder);
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrderMain(currentOrder);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });
    }
}
