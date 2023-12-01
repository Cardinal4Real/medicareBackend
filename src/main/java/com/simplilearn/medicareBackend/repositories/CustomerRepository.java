package com.simplilearn.medicareBackend.repositories;

import com.simplilearn.medicareBackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);
    Customer findByUsernameAndPassword(String username, String password);
}
