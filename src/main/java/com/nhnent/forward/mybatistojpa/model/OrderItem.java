package com.nhnent.forward.mybatistojpa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long orderId;
    private Integer limeNumber;
    private Long itemId;
    private Integer quantity;

}
