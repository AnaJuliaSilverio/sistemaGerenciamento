package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.TeacherRequestDTO;
import com.example.sistemaGerenciamento.dtos.TeacherResponseDTO;
import com.example.sistemaGerenciamento.models.Teachers;
import com.example.sistemaGerenciamento.repositories.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Spy
    ModelMapper modelMapper;
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private TeacherService teacherService;

    @Test
    void whenCreateATeacherShouldReturnTeacher(){
        TeacherRequestDTO teacherRequestDTO = new TeacherRequestDTO("Paula");
        TeacherResponseDTO teacherResponseDTO = teacherService.createTeacher(teacherRequestDTO);
        assertEquals("Paula",teacherResponseDTO.getName());
    }
    @Test
    void shouldReturnAllTeachers(){
        Teachers teachers = new Teachers(1L,"Kate");
        when(teacherRepository.findAll()).thenReturn(Collections.singletonList(teachers));
        List<TeacherResponseDTO> teacherResponseDTOS = teacherService.getAllTeachers();

        assertEquals("Kate",teacherResponseDTOS.get(0).getName());
        assertEquals(1L,teacherResponseDTOS.get(0).getId());
    }

    @Test
    void shouldReturnWhenfindyCoursesByTeacherName(){
        List<String> coursesNames = new ArrayList<>();
        coursesNames.add("Matemática");
        coursesNames.add("Geografia");

        when(teacherRepository.findCourseNamesByTeacherName(anyString())).thenReturn(coursesNames);

        List<String> courses = teacherService.findyCoursesByTeacherName("Ana");

        assertEquals(courses,coursesNames);

    }
}
