package com.nhnent.forward.mybatistojpa.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "OrderItems")
public class OrderItem {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private Integer quantity;


    /*
     * Composite-id class must implement serializable.
     * Composite-id class must define equals and hashCode methods.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "order_id")
        private Long orderId;

        @Column(name = "line_number")
        private Integer lineNumber;

    }

}
