package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Learner;
import com.example.demo.Repository.LearnerRepository;

@Service
public class LearnerService {
    @Autowired
    private LearnerRepository learnerRepository;

    public Learner findByEmailAndPassword(String email, String password) {
        // Implement logic to find mentor by email and password
        return learnerRepository.findByEmailAndPassword(email, password);
    }

    public Learner findByPhoneNumberAndPassword(String phoneNumber, String password) {
        // Implement logic to find mentor by phone number and password
        return learnerRepository.findByPhoneNumberAndPassword(phoneNumber, password);
    }

    public Learner saveLearner(Learner learner) {
        if (learnerRepository.existsByEmail(learner.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        if (learnerRepository.existsByPhoneNumber(learner.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number is already in use.");
        }
        return learnerRepository.save(learner);
    }

    public Learner updateProfile(Learner updatedProfile) {
        try {
            Learner existingProfile = learnerRepository.findById(updatedProfile.getId())
                    .orElseThrow(() -> new RuntimeException("Profile not found"));
    
            // Update only the fields that are present
            if (updatedProfile.getFirstName() != null) {
                existingProfile.setFirstName(updatedProfile.getFirstName());
            }
            if (updatedProfile.getLastName() != null) {
                existingProfile.setLastName(updatedProfile.getLastName());
            }
            if (updatedProfile.getPhoneNumber() != null) {
                existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
            }
            if (updatedProfile.getCountryName() != null) {
                existingProfile.setCountryName(updatedProfile.getCountryName());
            }
            if (updatedProfile.getGender() != null) {
                existingProfile.setGender(updatedProfile.getGender());
            }
            if (updatedProfile.getAge() != null) {
                existingProfile.setAge(updatedProfile.getAge());
            }
            if (updatedProfile.getDob() != null) {
                existingProfile.setDob(updatedProfile.getDob());
            }
            if (updatedProfile.getQualification() != null) {
                existingProfile.setQualification(updatedProfile.getQualification());
            }
            if (updatedProfile.getExamsInterestedIn() != null) {
                existingProfile.setExamsInterestedIn(updatedProfile.getExamsInterestedIn());
            }
    
            return learnerRepository.save(existingProfile);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            throw new RuntimeException("Error updating profile: " + e.getMessage());
        }
    }
    
}