package com.example.sistemaGerenciamento.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "students_id")
    private Students students;
    @OneToOne
    @JoinColumn(name = "courses_id")
    private Courses courses;
    @Column(name = "registration_date",nullable = false)
    private LocalDate registrationDate;
}
