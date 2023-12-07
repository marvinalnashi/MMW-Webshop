package com.codearise.iprwcshop.service.impl;

import com.codearise.iprwcshop.entity.OrderMain;
import com.codearise.iprwcshop.entity.ProductInOrder;
import com.codearise.iprwcshop.entity.ProductInfo;
import com.codearise.iprwcshop.enums.OrderStatusEnum;
import com.codearise.iprwcshop.enums.ResultEnum;
import com.codearise.iprwcshop.exception.ExceptionHandler;
import com.codearise.iprwcshop.repository.OrderRepository;
import com.codearise.iprwcshop.repository.ProductInOrderRepository;
import com.codearise.iprwcshop.repository.ProductInfoRepository;
import com.codearise.iprwcshop.repository.UserRepository;
import com.codearise.iprwcshop.service.OrderService;
import com.codearise.iprwcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Page<OrderMain> getAllOrders(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
    }

    @Override
    public Page<OrderMain> getOrderByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
    }

    @Override
    public Page<OrderMain> getUserOrder(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }

    @Override
    public Page<OrderMain> getPhoneOrder(String phone, Pageable pageable) {
        return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
    }

    @Override
    public OrderMain getOrder(Long orderId) {
        OrderMain orderMain = orderRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new ExceptionHandler(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public OrderMain finishOrder(Long orderId) {
        OrderMain orderMain = getOrder(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new ExceptionHandler(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(orderMain);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderMain cancelOrder(Long orderId) {
        OrderMain orderMain = getOrder(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new ExceptionHandler(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(orderMain);
        Iterable<ProductInOrder> products = orderMain.getProducts();
        for(ProductInOrder productInOrder : products) {
            ProductInfo productInfo = productInfoRepository.findByProductId(productInOrder.getProductId());
            if(productInfo != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);
    }
}
