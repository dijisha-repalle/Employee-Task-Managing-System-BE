package com.example.SpringDemoMavenApplication.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String department;

    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    private User role;

}
