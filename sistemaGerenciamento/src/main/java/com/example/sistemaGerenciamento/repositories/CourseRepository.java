package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Long> {
    @Query("SELECT c.name FROM Courses c " +
            "LEFT JOIN Registration r ON c.id = r.courses.id " +
            "WHERE r.id IS NULL")
    List<String> findCoursesWithoutStudents();
}
