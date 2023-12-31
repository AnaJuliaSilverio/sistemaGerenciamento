package com.example.sistemaGerenciamento.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RegistratioRequestDTO {
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;
}
