// Mentor.java
package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "mentors", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "phoneNumber")
})
@Getter
@Setter
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String gender;
    private Date dob;
    private Integer age;
    private String qualification;
    private String experience;
    private String linkedIn;
    private String exams;
    private String subjects;
    private String languages;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private String countryCode;
    private String countryName;
    private String password;
    // public boolean isEmpty() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    // }

}
