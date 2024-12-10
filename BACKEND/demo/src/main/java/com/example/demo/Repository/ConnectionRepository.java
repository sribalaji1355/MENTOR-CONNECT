package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    // Find connections by mentorId
    List<Connection> findByMentorId(Long mentorId);
    List<Connection> findByLearnerId(Long learnerId);
}

