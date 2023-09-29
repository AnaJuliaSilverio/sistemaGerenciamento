package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.StudentRequestDTO;
import com.example.sistemaGerenciamento.dtos.StudentResponseDTO;
import com.example.sistemaGerenciamento.services.StudentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)

public class StudentControllerTest {

    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private StudentsService studentsService;

    @Autowired
    private StudentController studentController;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shouldReturnCreatedStatusWhenITryCreateAStudent() throws Exception {
        StudentRequestDTO studentRequestDTO = new StudentRequestDTO("Janaina", LocalDate.parse("2020-05-05"),"Av a,123");
        when(studentsService.createStudent(studentRequestDTO)).thenReturn(new StudentResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequestDTO)))
                .andExpect(status().isCreated());;
    }
    @Test
    void shouldReturnBadRequestStatusWhenITryCreateAStudentWithWrongBody() throws Exception {
        StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
        when(studentsService.createStudent(studentRequestDTO)).thenReturn(new StudentResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequestDTO)))
                .andExpect(status().isBadRequest());;
    }
    @Test
    void shouldReturnOkStatusWhenITryToGetAllStudents() throws Exception {
        when(studentsService.getAllStudents()).thenReturn(Collections.singletonList(new StudentResponseDTO()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());;
    }
    @Test
    void shouldReturnOkStatusWhenITryToGetUnenrolledStudentNames() throws Exception{
        when(studentsService.findUnenrolledStudentNames()).thenReturn(new ArrayList<String>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/unenrolled")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());;
    }
    @Test
    void shouldReturnOkStatusWhenITryToGetStudentsEnrolledInMultipleCourses() throws Exception{
        when(studentsService.findStudentsEnrolledInMultipleCourses()).thenReturn(new ArrayList<String>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/multiple-courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());;
    }
}
