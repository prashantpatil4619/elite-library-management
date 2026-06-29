package com.example.library_management.entity;


import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String mobile;

    private String email;

    private String seatNumber;

    private Integer fees;

    private LocalDate admissionDate;

    private LocalDate expiryDate;

    private String paymentStatus;

    private String studentCode;



}
