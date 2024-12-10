package com.example.demo.Repository;

import com.example.demo.Entity.Mentor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    // Mentor findByEmail(String email); 
    List<Mentor> findAll();
    @Query("SELECT m FROM Mentor m WHERE " +
       "(:exam IS NULL OR m.exams LIKE %:exam%) OR " +
       "(:subject IS NULL OR m.subjects LIKE %:subject%) OR " +
       "(:language IS NULL OR m.languages LIKE %:language%)")
List<Mentor> findMentorsByFilters(
        @Param("exam") String exam,
        @Param("subject") String subject,
        @Param("language") String language);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Mentor findByPhoneNumberAndPassword(String phoneNumber, String password);
    Mentor findByEmailAndPassword(String email, String password);
}