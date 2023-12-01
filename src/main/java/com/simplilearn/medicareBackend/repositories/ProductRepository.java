package com.simplilearn.medicareBackend.repositories;

import com.simplilearn.medicareBackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByQuantityGreaterThan(int quantity);
}
