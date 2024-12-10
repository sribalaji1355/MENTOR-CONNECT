package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Learner;
import com.example.demo.Service.LearnerService;

@RestController
@RequestMapping("/api/learners")
@CrossOrigin(origins = "http://localhost:3000")
public class LearnerController {
    @Autowired
    private LearnerService learnerService;

    @GetMapping
    public ResponseEntity<Learner> login(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam String password) {

        // Check if email or phone number is provided
        Learner learner = null;
        if (email != null) {
            learner = learnerService.findByEmailAndPassword(email, password);
        } else if (phoneNumber != null) {
            learner = learnerService.findByPhoneNumberAndPassword(phoneNumber, password);
        }

        // If mentor is found, return it; otherwise, return 401 Unauthorized
        if (learner != null) {
            return ResponseEntity.ok(learner);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

     @PostMapping("/register")
    public ResponseEntity<?> registerLearner(@RequestBody Learner learner) {
        try {
            Learner savedLearner = learnerService.saveLearner(learner);
            return new ResponseEntity<>(savedLearner, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Learner> updateProfile(@RequestBody Learner updatedProfile) {
        try {
            // Call service method to update profile
            Learner savedProfile = learnerService.updateProfile(updatedProfile);
    
            // Return the updated profile as a response
            return ResponseEntity.ok(savedProfile);
        } catch (Exception e) {
            // Log the exception to get more details
            e.printStackTrace();
            // Return an error response in case of failure
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    
}
