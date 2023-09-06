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
    @Column(nullable = false)
    private Students students;
    @OneToOne
    @Column(nullable = false)
    private Courses courses;
    @Column(name = "registration_date",nullable = false)
    private LocalDate registrationDate;
}
