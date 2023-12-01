package com.simplilearn.medicareBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String contact;
    private String address;
    @OneToMany(mappedBy = "customer")
    @Column(name = "orderslist")
    private List<Orders> ordersList;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", contactNumber='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", ordersList=" + ordersList +
                '}';
    }
}
