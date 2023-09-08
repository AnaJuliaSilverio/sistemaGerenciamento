package com.example.sistemaGerenciamento.services;

import com.example.sistemaGerenciamento.dtos.CourseRequestDTO;
import com.example.sistemaGerenciamento.dtos.CourseResponseDTO;
import com.example.sistemaGerenciamento.models.Courses;
import com.example.sistemaGerenciamento.models.Teachers;
import com.example.sistemaGerenciamento.repositories.CourseRepository;
import com.example.sistemaGerenciamento.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO,Long teacherId){
      Teachers teachers = teacherRepository.findById(teacherId).orElseThrow(()-> new EntityNotFoundException("Nenhum professor identificado"));
      Courses courses = new Courses();
      modelMapper.map(courseRequestDTO,courses);
      courses.setTeachers(teachers);
      courseRepository.save(courses);
      return modelMapper.map(courses,CourseResponseDTO.class);
    }
    public List<String> findCoursesWithoutStudents(){
        return courseRepository.findCoursesWithoutStudents();
    }
}
