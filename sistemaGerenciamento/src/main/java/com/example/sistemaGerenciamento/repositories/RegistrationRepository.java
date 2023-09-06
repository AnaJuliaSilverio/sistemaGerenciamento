package com.example.sistemaGerenciamento.repositories;

import com.example.sistemaGerenciamento.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
}
