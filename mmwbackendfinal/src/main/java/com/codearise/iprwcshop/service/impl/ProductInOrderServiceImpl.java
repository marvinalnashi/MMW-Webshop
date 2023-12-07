package com.codearise.iprwcshop.service.impl;

import com.codearise.iprwcshop.entity.ProductInOrder;
import com.codearise.iprwcshop.entity.User;
import com.codearise.iprwcshop.repository.ProductInOrderRepository;
import com.codearise.iprwcshop.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, User user) {
        Optional<ProductInOrder> orderedProduct = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        orderedProduct.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });
    }

    @Override
    public ProductInOrder getProductInOrder(String itemId, User user) {
        Optional<ProductInOrder> orderedProduct = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> product = new AtomicReference<>();
        orderedProduct.ifPresent(product::set);
        return product.get();
    }
}
