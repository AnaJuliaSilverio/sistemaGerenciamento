package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.CourseRequestDTO;
import com.example.sistemaGerenciamento.dtos.CourseResponseDTO;
import com.example.sistemaGerenciamento.services.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private CourseService courseService;
    @Autowired
    private CourseController courseController;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(courseController)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shouldReturnCreatedStatusWhenITryCreateACourse() throws Exception{
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO("Matematica");
        when(courseService.createCourse(courseRequestDTO,1L)).thenReturn(new CourseResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDTO)))
                .andExpect(status().isCreated());;

    }

    @Test
    void shouldReturnBadRequestStatusWhenITryCreateAStudentWithWrongBody() throws Exception{
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
        when(courseService.createCourse(courseRequestDTO,1L)).thenReturn(new CourseResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDTO)))
                .andExpect(status().isBadRequest());;
    }

    @Test
    void shouldReturnOkStatusWhenITryToGetAllCourse() throws Exception{
        when(courseService.getAllCourses()).thenReturn(Collections.singletonList(new CourseResponseDTO()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());;
    }
    @Test
    void shouldReturnOkStatusWhenITryToGetCoursesWithoutStudent() throws Exception{
        when(courseService.findCoursesWithoutStudents()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/without-students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());;

    }
}
