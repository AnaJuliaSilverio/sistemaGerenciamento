package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.StudentRequestDTO;
import com.example.sistemaGerenciamento.dtos.StudentResponseDTO;
import com.example.sistemaGerenciamento.models.Students;
import com.example.sistemaGerenciamento.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;


    public StudentRequestDTO createStudent(StudentResponseDTO studentResponseDTO){
        Students students = new Students();
        modelMapper.map(studentResponseDTO,students);
        studentRepository.save(students);
        return modelMapper.map(students,StudentRequestDTO.class);
    }

    public List<String> findUnenrolledStudentNames(){
        return studentRepository.findUnenrolledStudentNames();
    }

    public List<String> findStudentsEnrolledInMultipleCourses(){
        return studentRepository.findStudentsEnrolledInMultipleCourses();
    }
}
