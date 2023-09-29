package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.StudentRequestDTO;
import com.example.sistemaGerenciamento.dtos.StudentResponseDTO;
import com.example.sistemaGerenciamento.models.Students;
import com.example.sistemaGerenciamento.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentsServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    private StudentsService studentsService;
    private Students students;
    @BeforeEach
    public void setUp(){
        students = new Students(1L,"Ana", LocalDate.parse("2020-10-10"),"Av teste,123");
    }
    @Test
    void whenCreateAStudentShouldReturnAStudent(){
        StudentRequestDTO studentRequestDTO = new StudentRequestDTO("Ana", LocalDate.parse("2020-10-10"),"Av teste,123");
        StudentResponseDTO studentResponseDTO = studentsService.createStudent(studentRequestDTO);
        assertEquals("Ana",studentResponseDTO.getName());
        assertEquals("Av teste,123",studentResponseDTO.getAddress());
    }

    @Test
    void shouldReturnAllStudents(){
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(students));
        List<StudentResponseDTO> allStudents = studentsService.getAllStudents();
        assertNotNull(allStudents);
        assertEquals("Ana",allStudents.get(0).getName());
    }
    @Test
    void shouldReturnUnenrolledStudentNames(){
        List<String> names = new ArrayList<>();
        names.add("Ana");
        names.add("Julia");

        when(studentRepository.findUnenrolledStudentNames()).thenReturn(names);
        List<String> namesReturns = studentsService.findUnenrolledStudentNames();

        assertEquals(names,namesReturns);
    }
    @Test
    void shouldReturnStudentsEnrolledInMultipleCourses(){
        List<String> names = new ArrayList<>();
        names.add("Maria");
        names.add("Paula");

        when(studentRepository.findStudentsEnrolledInMultipleCourses()).thenReturn(names);
        List<String> namesReturns = studentsService.findStudentsEnrolledInMultipleCourses();

        assertEquals(names,namesReturns);
    }
}
