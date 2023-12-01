package com.simplilearn.medicareBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private int quantity;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Orders> ordersList;

    public Product(String name, double price, int quantity, String url) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", url='" + url + '\'' +
                '}';
    }
}
