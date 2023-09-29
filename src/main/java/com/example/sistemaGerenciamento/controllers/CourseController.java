package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.CourseRequestDTO;
import com.example.sistemaGerenciamento.dtos.CourseResponseDTO;
import com.example.sistemaGerenciamento.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/{teacherId}")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO, @PathVariable(value = "teacherId") Long teacherId){
        CourseResponseDTO courseResponseDTO = courseService.createCourse(courseRequestDTO,teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDTO);
    }
    @GetMapping("/without-students")
    public ResponseEntity<List<String>> findCoursesWithoutStudent(){
        List<String> courses = courseService.findCoursesWithoutStudents();
        return ResponseEntity.ok().body(courses);
    }
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }
}
