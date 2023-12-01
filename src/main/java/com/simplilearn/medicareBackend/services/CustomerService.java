package com.simplilearn.medicareBackend.services;

import com.simplilearn.medicareBackend.entities.Customer;
import com.simplilearn.medicareBackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}
