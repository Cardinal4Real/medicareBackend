package com.simplilearn.medicareBackend.controllers;

import com.simplilearn.medicareBackend.dtos.Login;
import com.simplilearn.medicareBackend.entities.Admin;
import com.simplilearn.medicareBackend.entities.Customer;
import com.simplilearn.medicareBackend.entities.Product;
import com.simplilearn.medicareBackend.repositories.AdminLoginRepository;
import com.simplilearn.medicareBackend.repositories.CustomerRepository;
import com.simplilearn.medicareBackend.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class AdminController {
    @Autowired
    AdminLoginRepository adminRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    String msg = "";

    @GetMapping("/admin/landingpage")
    public String adminLoginLandingPage(Model model) {
        model.addAttribute("home", "active");
        return "adminLogin";
    }

    @PostMapping(value = {"/admin/login"})
    public String loginAdmin(@ModelAttribute Login login, Model model, HttpSession session) {
        System.out.println(login);
        String nav = "";
        Admin admin = adminRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
        if (admin != null) {
            model.addAttribute("user", admin);
            session.setAttribute("usersession", admin);
            Boolean isNew = session.isNew();
            System.out.println(isNew);
            nav = "redirect:/admin/home";
        } else {
            model.addAttribute("msg", "Login failed...pls try again");
            nav = "adminLogin";
        }

        return nav;
    }

    @GetMapping("/admin/home")
    public String homePage(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        model.addAttribute("home", "active");
        return "adminHome";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(@ModelAttribute Product product, Model model, HttpSession session) {
        String id = session.getId();
        System.out.println(id);
        model.addAttribute("addProduct", "active");
        model.addAttribute("msg", msg);
        return "addProduct";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@ModelAttribute Product product, Model model, HttpSession session) {
        String id = session.getId();
        System.out.println("Adding product");
        productRepository.save(product);
        msg = "Product Added";
        return "redirect:/admin/addProduct";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("prod", product.get());
        System.out.println(product.get());
        return "updateProduct";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("prod", product.get());
        return "deleteProduct";
    }

    @PostMapping("/admin/updateProduct")
    public String updateProduct(@ModelAttribute Product product, Model model) {
        Optional<Product> productF = productRepository.findById(product.getId());
        if (productF.isPresent()) {
            Product prodMod = productF.get();
            System.out.println("Product to be modified is " + prodMod);
            prodMod.setName(product.getName());
            prodMod.setPrice(product.getPrice());
            prodMod.setQuantity(product.getQuantity());
            prodMod.setUrl(product.getUrl());
            productRepository.save(prodMod);
            model.addAttribute("msg", "Product Updated");
        } else {
            // Handle the case where the product with the provided ID doesn't exist.
            model.addAttribute("msg", "Product not found");
        }
        return "redirect:/admin/home";
    }

    @PostMapping("/admin/deleteProduct")
    public String deleteProduct(@ModelAttribute Product product, Model model) {
        Optional<Product> productF = productRepository.findById(product.getId());
        if (productF.isPresent()) {
            productRepository.delete(productF.get());
        } else {
            // Handle the case where the product with the provided ID doesn't exist.
            model.addAttribute("msg", "Product not found");
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/users/view")
    public String viewUsers(Model model) {
        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("userList", customerList);
        model.addAttribute("viewUsers", "active");
        return "viewUsers";
    }
}
