package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.StudentRequestDTO;
import com.example.sistemaGerenciamento.dtos.StudentResponseDTO;
import com.example.sistemaGerenciamento.services.StudentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentsService studentsService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody @Valid StudentRequestDTO studentRequestDTO){
        StudentResponseDTO studentResponseDTO = studentsService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDTO);
    }
    @GetMapping("/unenrolled")
    public ResponseEntity<List<String>> findUnenrolledStudentNames(){
        List<String> studentsNames = studentsService.findUnenrolledStudentNames();
        return ResponseEntity.ok().body(studentsNames);
    }
    @GetMapping("/multiple-courses")
    public ResponseEntity<List<String>> findStudentsEnrolledInMultipleCourses() {
        List<String> studentsNames = studentsService.findStudentsEnrolledInMultipleCourses();
        return ResponseEntity.ok().body(studentsNames);
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        return ResponseEntity.ok().body(studentsService.getAllStudents());
    }
}
