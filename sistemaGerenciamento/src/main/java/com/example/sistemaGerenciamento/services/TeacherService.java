package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.TeacherRequestDTO;
import com.example.sistemaGerenciamento.dtos.TeacherResponseDTO;
import com.example.sistemaGerenciamento.models.Teachers;
import com.example.sistemaGerenciamento.repositories.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO){
        Teachers teachers = new Teachers();
        modelMapper.map(teacherRequestDTO,teachers);
        teacherRepository.save(teachers);
        return modelMapper.map(teachers, TeacherResponseDTO.class);
    }
    public List<TeacherResponseDTO> getAllTeachers(){
        return teacherRepository.findAll().stream()
                .map(teachers -> modelMapper.map(teachers,TeacherResponseDTO.class)).toList();
    }
    public List<String> findyCoursesByTeacherName(String teacherName){
        return teacherRepository.findCourseNamesByTeacherName(teacherName);
    }
}
