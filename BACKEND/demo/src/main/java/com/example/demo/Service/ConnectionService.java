package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Connection;
import com.example.demo.Repository.ConnectionRepository;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    public Connection createConnection(Long learnerId, String learnerName, String learnerEmail,
                                       String learnerPhoneNumber, Long mentorId, String mentorName,
                                       String mentorEmail, String mentorPhoneNumber) {

        // Create a new connection object
        Connection connection = new Connection();
        connection.setLearnerId(learnerId);
        connection.setLearnerName(learnerName);
        connection.setLearnerEmail(learnerEmail);
        connection.setLearnerPhoneNumber(learnerPhoneNumber);

        connection.setMentorId(mentorId);
        connection.setMentorName(mentorName);
        connection.setMentorEmail(mentorEmail);
        connection.setMentorPhoneNumber(mentorPhoneNumber);

        connection.setRequestStatus("Raised"); // Initial status is "Raised"

        // Save to the database
        return connectionRepository.save(connection);
    }

    // Get all connections (requests) for a learner
    public List<Connection> getConnectionsByLearner(Long learnerId) {
        return connectionRepository.findByLearnerId(learnerId);
    }

    public List<Connection> getConnectionsByMentor(Long mentorId) {
        return connectionRepository.findByMentorId(mentorId); // Fetching by mentorId
    }

    public Connection acceptConnection(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new RuntimeException("Connection not found"));
        connection.setRequestStatus("Accepted");
        connection.setGoogleMeetLink(generateGoogleMeetLink());  // Example link
        connection.setMentorResponse("Request accepted. A Google Meet link will be shared.");
        return connectionRepository.save(connection);
    }

    public Connection rejectConnection(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new RuntimeException("Connection not found"));
        connection.setRequestStatus("Rejected");
        connection.setMentorResponse("Sorry, your request has been rejected.");
        return connectionRepository.save(connection);
    }



    private String generateGoogleMeetLink() {
        // Generate a real Google Meet link using Google APIs or return a static one for now
        return "https://meet.google.com/abc-defg-hij";  // Static for example purposes
    }
}

