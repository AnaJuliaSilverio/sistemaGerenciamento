package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.RegistratioRequestDTO;
import com.example.sistemaGerenciamento.dtos.RegistrationResponseDTO;
import com.example.sistemaGerenciamento.models.Courses;
import com.example.sistemaGerenciamento.models.Registration;
import com.example.sistemaGerenciamento.models.Students;
import com.example.sistemaGerenciamento.models.Teachers;
import com.example.sistemaGerenciamento.repositories.CourseRepository;
import com.example.sistemaGerenciamento.repositories.RegistrationRepository;
import com.example.sistemaGerenciamento.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private RegistrationRepository registrationRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private RegistrationService registrationService;
    private Teachers teachers;
    private Courses courses;
    private Students students;
    @BeforeEach
    public void setUp(){
        teachers= new Teachers(1L,"Ana");
       courses = new Courses(1L,"Matemática",teachers);
        students = new Students(1L,"Ana", LocalDate.parse("2020-10-10"),"Av teste,123");
    }

    @Test
    void whenCreateRegistrationShouldReturnRegistration(){
        RegistratioRequestDTO registratioRequestDTO = new RegistratioRequestDTO(LocalDate.parse("2020-10-10"));

        when(studentRepository.findById(1L)).thenReturn(Optional.of(students));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(courses));

        RegistrationResponseDTO registrationResponseDTO = registrationService.createRegistration(registratioRequestDTO,1L,1L);

        assertEquals(courses,registrationResponseDTO.getCourses());
        assertEquals(students,registrationResponseDTO.getStudents());
    }

    @Test
    void whenCreateRegistrationWithInvalidStudentIdShouldThrowException(){
        RegistratioRequestDTO registratioRequestDTO = new RegistratioRequestDTO(LocalDate.parse("2020-10-10"));

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> registrationService.createRegistration(registratioRequestDTO,1L,1L));
    }
    @Test
    void whenCreateRegistrationWithInvalidCourseIdShouldThrowException(){
        RegistratioRequestDTO registratioRequestDTO = new RegistratioRequestDTO(LocalDate.parse("2020-10-10"));

        when(studentRepository.findById(1L)).thenReturn(Optional.of(students));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> registrationService.createRegistration(registratioRequestDTO,1L,1L));
    }
    @Test
    void shouldReturnAllRegistrations(){
        Registration registration = new Registration(1L,students,courses,LocalDate.parse("2020-10-10"));

        when(registrationRepository.findAll()).thenReturn(Collections.singletonList(registration));

        List<RegistrationResponseDTO> responseDTOList = registrationService.getAllRegistration();

        assertEquals(courses,responseDTOList.get(0).getCourses());
        assertEquals(students,responseDTOList.get(0).getStudents());
    }
    @Test
    void shouldReturnStudentNamesByCourseName(){
        List<String> names = new ArrayList<>();
        names.add("Ana");
        names.add("Julia");

        when(registrationRepository.findStudentNamesByCourseName(anyString())).thenReturn(names);

        List<String> students = registrationService.findStudentNamesByCourseName("Matemática");

        assertEquals(students,names);
    }
}
