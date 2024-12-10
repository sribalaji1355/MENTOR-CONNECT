package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Connection;
import com.example.demo.Service.ConnectionService;

@RestController
@RequestMapping("/api/connections")
@CrossOrigin(origins = "http://localhost:3000")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Connection createConnection(@RequestBody Map<String, Object> request) {
        // Retrieve each field from the map and cast to the appropriate type
        Long learnerId = ((Number) request.get("learnerId")).longValue();
        String learnerName = (String) request.get("learnerName");
        String learnerEmail = (String) request.get("learnerEmail");
        String learnerPhoneNumber = (String) request.get("learnerPhoneNumber");
        Long mentorId = ((Number) request.get("mentorId")).longValue();
        String mentorName = (String) request.get("mentorName");
        String mentorEmail = (String) request.get("mentorEmail");
        String mentorPhoneNumber = (String) request.get("mentorPhoneNumber");
        
        // Optional: if 'requestStatus' is included
        String requestStatus = (String) request.getOrDefault("requestStatus", "Raised");

        // Call the service with the extracted parameters
        return connectionService.createConnection(
            learnerId, learnerName, learnerEmail, learnerPhoneNumber,
            mentorId, mentorName, mentorEmail, mentorPhoneNumber
        );


    }
        // Get all connections (requests) for a learner
    @GetMapping("/{learnerId}")
    public List<Connection> getConnections(@PathVariable Long learnerId) {
        return connectionService.getConnectionsByLearner(learnerId);
    }

    @GetMapping("/mentor/{mentorId}")
    public List<Connection> getRequestsForMentor(@PathVariable Long mentorId) {
        return connectionService.getConnectionsByMentor(mentorId);
    }

    @PutMapping("/accept/{connectionId}")
    public Connection acceptConnection(@PathVariable Long connectionId) {
        return connectionService.acceptConnection(connectionId);
    }

    @PutMapping("/reject/{connectionId}")
    public Connection rejectConnection(@PathVariable Long connectionId) {
        return connectionService.rejectConnection(connectionId);
    }
}

