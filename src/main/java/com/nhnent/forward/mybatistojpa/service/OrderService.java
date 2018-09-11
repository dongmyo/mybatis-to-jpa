package com.nhnent.forward.mybatistojpa.service;

import com.nhnent.forward.mybatistojpa.entity.Order;
import com.nhnent.forward.mybatistojpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;


    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    @Transactional
    public Order createOrder(Order order) {
        int count = orderMapper.insertOrder(order);
        if (count != 1) {
            throw new RuntimeException("can't create order");
        }

        order.getOrderItems()
             .forEach(orderItem -> {
                 orderItem.setOrderId(order.getOrderId());
                 orderItemMapper.insertOrderItem(orderItem);
             });

        return order;
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderItemMapper.deleteOrderItem(orderId);
        orderMapper.deleteOrder(orderId);
    }
}
