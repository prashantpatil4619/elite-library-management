package com.example.library_management.service;

import com.example.library_management.entity.Student;

import com.example.library_management.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

import java.util.Map;

import java.util.HashMap;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // GET ALL STUDENTS

    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    // ADD STUDENT

    public Student addStudent(Student student) {

        long count =
                studentRepository.count();

        String code =
                "ELT" + (1001 + count);

        student.setStudentCode(code);

        return studentRepository
                .save(student);
    }

    // CHECK SEAT BOOKED

    public boolean
    isSeatBooked(String seatNumber) {

        return studentRepository
                .existsBySeatNumber(
                        seatNumber
                );
    }

    // EXPIRED STUDENTS

    public List<Student>
    getExpiredStudents() {

        return studentRepository
                .findByExpiryDateBefore(
                        LocalDate.now()
                );
    }

    // DASHBOARD STATS

    // DASHBOARD STATS

    public Map<String, Object>
    getDashboardStats() {

        Map<String, Object> stats =
                new HashMap<>();

        // ONLY PAID ₹1500

        long premiumBooked =

                studentRepository
                        .countByFeesAndPaymentStatus(
                                1500,
                                "PAID"
                        );

        long premiumRemaining =
                40 - premiumBooked;

        // ONLY PAID ₹1200

        long normalBooked =

                studentRepository
                        .countByFeesAndPaymentStatus(
                                1200,
                                "PAID"
                        );

        long normalRemaining =
                40 - normalBooked;

        // TOTAL BOOKED

        long totalBooked =

                premiumBooked
                        +
                        normalBooked;

        // TOTAL AVAILABLE

        long totalAvailable =
                80 - totalBooked;

        // REVENUE

        long revenue =

                (premiumBooked * 1500)

                        +

                        (normalBooked * 1200);

        // SAVE

        stats.put(
                "premiumBooked",
                premiumBooked
        );

        stats.put(
                "premiumRemaining",
                premiumRemaining
        );

        stats.put(
                "normalBooked",
                normalBooked
        );

        stats.put(
                "normalRemaining",
                normalRemaining
        );

        stats.put(
                "totalBooked",
                totalBooked
        );

        stats.put(
                "totalAvailable",
                totalAvailable
        );

        stats.put(
                "revenue",
                revenue
        );

        return stats;
    }

    // DELETE STUDENT

    public void deleteStudent(Long id) {

        studentRepository
                .deleteById(id);
    }

    // UPDATE PAYMENT STATUS

    public Student
    updatePaymentStatus(

            Long id,

            String status

    ) {

        Student student =

                studentRepository
                        .findById(id)
                        .orElseThrow();

        student.setPaymentStatus(status);

        return studentRepository
                .save(student);
    }
}