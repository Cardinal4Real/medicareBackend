package com.simplilearn.medicareBackend.controllers;

import com.simplilearn.medicareBackend.dtos.Login;
import com.simplilearn.medicareBackend.entities.Customer;
import com.simplilearn.medicareBackend.entities.Product;
import com.simplilearn.medicareBackend.repositories.CustomerRepository;
import com.simplilearn.medicareBackend.repositories.ProductRepository;
import com.simplilearn.medicareBackend.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class MedicareController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping(value = {"/login"}, consumes = {"text/xml", "application/json"}, produces = {"text/xml", "application/json"})
    public ResponseEntity<?> login(@RequestBody Login login) {
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());
        ResponseEntity.BodyBuilder resultat;
        Customer customer = customerRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
        if (customer == null) {
            resultat = ResponseEntity.badRequest();
        } else {
            resultat = ResponseEntity.ok();
            log.info("{}", customer);
        }
        return resultat.body(customer);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
        String resp = "";
        try {
            Customer saveCust = customerService.addCustomer(customer);
            resp = "Sign-up successful";
            log.info("{}", saveCust);
        } catch (Exception e) {
            resp = "Username already exists";
        }
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/findall")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/products/findall")

    public List<Product> getProducts() {
        return productRepository.findAllByQuantityGreaterThan(0);
    }
}
