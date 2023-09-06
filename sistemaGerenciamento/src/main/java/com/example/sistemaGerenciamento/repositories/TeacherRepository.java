package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers,Long> {
}
