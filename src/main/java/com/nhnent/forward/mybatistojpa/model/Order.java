package com.nhnent.forward.mybatistojpa.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
    private Long orderId;
    private Date orderDate;

}
