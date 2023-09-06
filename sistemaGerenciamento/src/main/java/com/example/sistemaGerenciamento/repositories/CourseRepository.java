package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Long> {
}
