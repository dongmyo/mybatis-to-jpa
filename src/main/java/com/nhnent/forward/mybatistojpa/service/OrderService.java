package com.nhnent.forward.mybatistojpa.service;

import com.nhnent.forward.mybatistojpa.entity.ItemEntity;
import com.nhnent.forward.mybatistojpa.entity.OrderEntity;
import com.nhnent.forward.mybatistojpa.entity.OrderItemEntity;
import com.nhnent.forward.mybatistojpa.model.Order;
import com.nhnent.forward.mybatistojpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getOrders(Pageable pageable) {
        Page<OrderEntity> orderPage = orderRepository.findAll(pageable);

        return orderPage.getContent()
                        .stream()
                        .map(OrderEntity::toOrderDto)
                        .collect(Collectors.toList());
    }

    public Order getOrder(Long orderId) {
        return Optional.ofNullable(orderRepository.findOne(orderId))
                       .map(OrderEntity::toOrderDto)
                       .orElse(null);
    }

    @Transactional
    public Order createOrder(Order orderDto) {
        OrderEntity order = new OrderEntity();
        order.setOrderDate(new Date());

        orderDto.getOrderItems()
             .forEach(orderItemDto -> {
                 ItemEntity item = new ItemEntity();
                 item.setItemId(orderItemDto.getItem().getItemId());

                 OrderItemEntity orderItem = new OrderItemEntity();
                 orderItem.setOrder(order);
                 orderItem.getPk().setLineNumber(orderItemDto.getLineNumber());
                 orderItem.setItem(item);
                 orderItem.setQuantity(orderItemDto.getQuantity());

                 order.getOrderItems().add(orderItem);
             });

        OrderEntity newOrder = orderRepository.save(order);
        return newOrder.toOrderDto();
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.delete(orderId);
    }
}
