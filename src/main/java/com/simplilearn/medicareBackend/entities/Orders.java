package com.simplilearn.medicareBackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    @Column(name = "order_cost")
    private double orderCost;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    @CreationTimestamp
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", orderCost=" + orderCost +
                ", timestamp=" + timestamp +
                '}';
    }
}
