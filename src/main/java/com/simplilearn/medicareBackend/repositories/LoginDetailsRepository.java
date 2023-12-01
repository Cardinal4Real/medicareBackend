package com.simplilearn.medicareBackend.repositories;

import com.simplilearn.medicareBackend.entities.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails,Long> {

}
