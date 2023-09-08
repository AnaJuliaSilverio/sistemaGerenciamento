package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.RegistratioRequestDTO;
import com.example.sistemaGerenciamento.dtos.RegistrationResponseDTO;
import com.example.sistemaGerenciamento.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;


    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<RegistrationResponseDTO> createRegistration(@RequestBody @Valid RegistratioRequestDTO registrationRequestDTO, @PathVariable Long studentId, @PathVariable Long courseId) {
       RegistrationResponseDTO registrationResponseDTO= registrationService.createRegistration(registrationRequestDTO, studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponseDTO);
    }

    @GetMapping("/students/{courseName}")
    public ResponseEntity<List<String>> findStudentNamesByCourseName(@PathVariable(value = "courseName") String courseName) {
        List<String> studentNames = registrationService.findStudentNamesByCourseName(courseName);
        return ResponseEntity.ok().body(studentNames);
    }

}
