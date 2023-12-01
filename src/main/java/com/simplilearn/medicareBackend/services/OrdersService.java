package com.simplilearn.medicareBackend.services;

import com.simplilearn.medicareBackend.entities.Orders;
import com.simplilearn.medicareBackend.entities.Product;
import com.simplilearn.medicareBackend.repositories.OrdersRepository;
import com.simplilearn.medicareBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;

    public void addOrders(List<Orders> ordersList) {
        for (Orders ord : ordersList) {
            Long prodId = ord.getProduct().getId();
            int quantity = ord.getQuantity();
            Optional<Product> product = productRepository.findById(prodId);
            if (product.isPresent()) {
                Product modifyProd = product.get();
                int newQuan = modifyProd.getQuantity() - quantity;
                modifyProd.setQuantity(newQuan);
                productRepository.save(modifyProd);
            }
        }
        ordersRepository.saveAll(ordersList);
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }
}
