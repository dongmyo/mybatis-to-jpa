package com.nhnent.forward.mybatistojpa.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    List<OrderItem> orderItems;

}
