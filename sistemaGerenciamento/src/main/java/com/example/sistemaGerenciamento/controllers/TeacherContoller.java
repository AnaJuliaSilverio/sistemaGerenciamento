package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.TeacherRequestDTO;
import com.example.sistemaGerenciamento.dtos.TeacherResponseDTO;
import com.example.sistemaGerenciamento.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherContoller {
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody @Valid TeacherRequestDTO teacherRequestDTO){
        TeacherResponseDTO teacherResponseDTO = teacherService.createTeacher(teacherRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherResponseDTO);
    }
    @GetMapping("/{teacherName}")
    public ResponseEntity<List<String>> findyCoursesByTeacherName(@Param(value = "teacherName")String teacherName){
        List<String> teachers = teacherService.findyCoursesByTeacherName(teacherName);
        return ResponseEntity.ok().body(teachers);
    }

}
