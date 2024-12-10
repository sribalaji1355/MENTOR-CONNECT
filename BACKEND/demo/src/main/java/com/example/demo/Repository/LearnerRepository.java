package com.example.demo.Repository;

import com.example.demo.Entity.Learner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Learner findByPhoneNumberAndPassword(String phoneNumber, String password);
    Learner findByEmailAndPassword(String email, String password);
}