package com.example.sistemaGerenciamento.dtos;

import com.example.sistemaGerenciamento.models.Teachers;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private Teachers teachers;
}
