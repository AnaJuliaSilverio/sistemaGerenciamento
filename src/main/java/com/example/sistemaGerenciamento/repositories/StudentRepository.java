package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
    @Query("SELECT s.name FROM Students s " +
            "LEFT JOIN Registration r ON s.id = r.students.id " +
            "WHERE r.id IS NULL")
    List<String> findUnenrolledStudentNames();
    @Query("SELECT s.name FROM Students s " +
            "INNER JOIN Registration r ON s.id = r.students.id " +
            "GROUP BY s.name " +
            "HAVING COUNT(r.courses.id) > 1")
    List<String> findStudentsEnrolledInMultipleCourses();
}
