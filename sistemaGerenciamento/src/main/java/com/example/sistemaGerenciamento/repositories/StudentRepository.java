package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
}
