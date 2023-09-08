package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.CourseRequestDTO;
import com.example.sistemaGerenciamento.dtos.CourseResponseDTO;
import com.example.sistemaGerenciamento.models.Courses;
import com.example.sistemaGerenciamento.models.Teachers;
import com.example.sistemaGerenciamento.repositories.CourseRepository;
import com.example.sistemaGerenciamento.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private CourseRepository courseRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private CourseService courseService;

    @Test
    void whenCreateACourseShouldReturnCourse(){
        Teachers teachers= new Teachers(1L,"Ana");
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teachers));
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO("Matemática");
        CourseResponseDTO courseResponseDTO = courseService.createCourse(courseRequestDTO,1L);

        assertEquals("Matemática",courseResponseDTO.getName());
        assertEquals(teachers,courseResponseDTO.getTeachers());
    }
    @Test
    void whenCreateACourseWithInvalidIdShouldThrowAnException(){
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO("Matemática");
        when(teacherRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> courseService.createCourse(courseRequestDTO,99L));
    }
    @Test
    void shouldReturnAllCourses(){
        Teachers teachers= new Teachers(1L,"Ana");
        Courses courses = new Courses(1L,"Matemática",teachers);

        when(courseRepository.findAll()).thenReturn(Collections.singletonList(courses));

        List<CourseResponseDTO> courseResponseDTO = courseService.getAllCourses();

        assertEquals("Matemática",courseResponseDTO.get(0).getName());
        assertEquals(teachers,courseResponseDTO.get(0).getTeachers());
    }

    @Test
    void shouldReturnCoursesWithoutStudents(){
        List<String> coursesNames = new ArrayList<>();
        coursesNames.add("Matemática");
        coursesNames.add("Geografia");

        when(courseRepository.findCoursesWithoutStudents()).thenReturn(coursesNames);
        List<String> course = courseService.findCoursesWithoutStudents();

        assertEquals(course,coursesNames);
    }
}
