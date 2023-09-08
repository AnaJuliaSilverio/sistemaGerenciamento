package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    @Query("SELECT s.name FROM Students s " +
            "INNER JOIN Registration r ON s.id = r.students.id " +
            "INNER JOIN Courses c ON r.courses.id = c.id " +
            "WHERE c.name = :courseName")
    List<String> findStudentNamesByCourseName(@Param("courseName") String courseName);

}
