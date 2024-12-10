package com.example.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "connections")
@Getter
@Setter
public class Connection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long learnerId;
    private String learnerName;
    private String learnerEmail;
    private String learnerPhoneNumber;

    private Long mentorId;
    private String mentorName;
    private String mentorEmail;
    private String mentorPhoneNumber;

    private String requestStatus;

    private String googleMeetLink;  // For accepted requests
    private String mentorResponse;
}
