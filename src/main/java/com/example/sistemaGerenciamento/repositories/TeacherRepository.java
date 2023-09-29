package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers,Long> {
    @Query("SELECT c.name FROM Courses c " +
            "INNER JOIN c.teachers t " +
            "WHERE t.name = :teacherName")
    List<String> findCourseNamesByTeacherName(@Param("teacherName") String teacherName);
}
