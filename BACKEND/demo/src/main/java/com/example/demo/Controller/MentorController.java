// MentorController.java
package com.example.demo.Controller;

import com.example.demo.Entity.Mentor;
import com.example.demo.Service.MentorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors")
@CrossOrigin(origins = "http://localhost:3000") // Adjust if your frontend is hosted elsewhere
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/getall")
    public List<Mentor> getall()
    {
        return mentorService.getall();
    }

    @GetMapping
    public ResponseEntity<Mentor> login(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam String password) {

        // Check if email or phone number is provided
        Mentor mentor = null;
        if (email != null) {
            mentor = mentorService.findByEmailAndPassword(email, password);
        } else if (phoneNumber != null) {
            mentor = mentorService.findByPhoneNumberAndPassword(phoneNumber, password);
        }

        // If mentor is found, return it; otherwise, return 401 Unauthorized
        if (mentor != null) {
            return ResponseEntity.ok(mentor);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // Endpoint to get details of all mentors by email
    // @GetMapping("/getmentor")
    // public ResponseEntity<Mentor> getMentor(@RequestParam String email, @RequestParam String password) {
    //     Mentor mentors = mentorService.findMentor(email, password);

    //     // If mentors are found with the given email, return them; otherwise, return 404 Not Found
    //     if (mentors != null ) {
    //         return ResponseEntity.ok(mentors);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     }
    // }

    @GetMapping("/{id}")
    public Mentor getMentorById(@PathVariable Long id) {
        return mentorService.getMentorById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerLearner(@RequestBody Mentor mentor) {
        try {
            Mentor savedMentor = mentorService.saveMentor(mentor);
            return new ResponseEntity<>(savedMentor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Mentor> updateProfile(@RequestBody Mentor updatedProfile) {
        try {
            // Call service method to update profile
            Mentor savedProfile = mentorService.updateProfile(updatedProfile);
    
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
