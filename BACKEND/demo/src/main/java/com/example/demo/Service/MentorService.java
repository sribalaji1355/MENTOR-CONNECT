// MentorService.java
package com.example.demo.Service;

import com.example.demo.Entity.Learner;
import com.example.demo.Entity.Mentor;
import com.example.demo.Repository.MentorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    public Mentor findByEmailAndPassword(String email, String password) {
        // Implement logic to find mentor by email and password
        return mentorRepository.findByEmailAndPassword(email, password);
    }

    public Mentor findByPhoneNumberAndPassword(String phoneNumber, String password) {
        // Implement logic to find mentor by phone number and password
        return mentorRepository.findByPhoneNumberAndPassword(phoneNumber, password);
    }

    public List<Mentor> getall()
    {
        return mentorRepository.findAll();
    }

    public Mentor getMentorById(Long id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("Mentor not found with id: " + id));
    }

    public class MentorNotFoundException extends RuntimeException {
        public MentorNotFoundException(String message) {
            super(message);
        }
    }
    
    public Mentor updateProfile(Mentor updatedProfile) {
        try {
            Mentor existingProfile = mentorRepository.findById(updatedProfile.getId())
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
            if (updatedProfile.getExams() != null) {
                existingProfile.setExams(updatedProfile.getExams());
            }
            if (updatedProfile.getLanguages() != null) {
                existingProfile.setLanguages(updatedProfile.getLanguages());
            }
            if (updatedProfile.getLinkedIn() != null) {
                existingProfile.setLinkedIn(updatedProfile.getLinkedIn());
            }
            if (updatedProfile.getSubjects() != null) {
                existingProfile.setSubjects(updatedProfile.getSubjects());
            }
    
            return mentorRepository.save(existingProfile);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            throw new RuntimeException("Error updating profile: " + e.getMessage());
        }
    }
    

    // public Mentor findMentor(String email, String Password) {
    //     return mentorRepository.findByEmail(email);
    // }

    public Mentor saveMentor(Mentor mentor) {
        if (mentorRepository.existsByEmail(mentor.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        if (mentorRepository.existsByPhoneNumber(mentor.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number is already in use.");
        }
        return mentorRepository.save(mentor);
    }
}
