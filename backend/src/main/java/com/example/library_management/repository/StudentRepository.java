package com.example.library_management.repository;

import com.example.library_management.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student>
    findByMobile(String mobile);

    List<Student>
    findByExpiryDateBefore(
            LocalDate date
    );

    boolean existsBySeatNumber(
            String seatNumber
    );

    // COUNT PAID ₹1500

    long countByFeesAndPaymentStatus(

            int fees,

            String paymentStatus
    );
}