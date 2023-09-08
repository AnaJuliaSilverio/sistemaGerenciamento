package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.RegistratioRequestDTO;
import com.example.sistemaGerenciamento.dtos.RegistrationResponseDTO;
import com.example.sistemaGerenciamento.models.Courses;
import com.example.sistemaGerenciamento.models.Registration;
import com.example.sistemaGerenciamento.models.Students;
import com.example.sistemaGerenciamento.repositories.CourseRepository;
import com.example.sistemaGerenciamento.repositories.RegistrationRepository;
import com.example.sistemaGerenciamento.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RegistratioRequestDTO createRegistration(RegistrationResponseDTO registrationResponseDTO,Long studentId,Long courseId){
        Students students = studentRepository.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Nenhum estudante identificado"));
        Courses courses = courseRepository.findById(courseId).orElseThrow(()-> new EntityNotFoundException("Nenhum curso identificado"));

        Registration registration = new Registration();
        modelMapper.map(registrationResponseDTO,registration);

        registration.setCourses(courses);
        registration.setStudents(students);

        registrationRepository.save(registration);

        return modelMapper.map(registration,RegistratioRequestDTO.class);
    }

    public List<String> findStudentNamesByCourseName(String courseName){
        return  registrationRepository.findStudentNamesByCourseName(courseName);
    }
}
