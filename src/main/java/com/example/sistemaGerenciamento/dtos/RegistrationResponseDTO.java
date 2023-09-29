package com.example.sistemaGerenciamento.dtos;

import com.example.sistemaGerenciamento.models.Courses;
import com.example.sistemaGerenciamento.models.Students;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDTO {
    @NotNull
    private Long id;
    @NotBlank
    private Students students;
    @NotBlank
    private Courses courses;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;
}
