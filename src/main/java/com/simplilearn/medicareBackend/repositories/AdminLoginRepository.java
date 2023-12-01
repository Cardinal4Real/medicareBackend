package com.simplilearn.medicareBackend.repositories;

import com.simplilearn.medicareBackend.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    Admin findByUsernameAndPassword(String username, String password);
}
