package com.nhnent.forward.mybatistojpa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long itemId;
    private String itemName;
    private Long price;

}
