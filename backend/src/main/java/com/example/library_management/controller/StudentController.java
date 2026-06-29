package com.example.library_management.controller;

import com.example.library_management.entity.Student;
import com.example.library_management.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/students")

@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)

public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET ALL STUDENTS

    @GetMapping
    public List<Student> getAllStudents() {

        return studentService
                .getAllStudents();
    }

    // ADD STUDENT

    @PostMapping
    public Student addStudent(

            @RequestBody Student student

    ) {

        return studentService
                .addStudent(student);
    }

    // CHECK SEAT BOOKED

    @GetMapping(
            "/check-seat/{seatNumber}"
    )

    public boolean checkSeat(

            @PathVariable String seatNumber

    ) {

        return studentService
                .isSeatBooked(seatNumber);
    }

    // EXPIRED STUDENTS

    @GetMapping("/expired")

    public List<Student>
    getExpiredStudents() {

        return studentService
                .getExpiredStudents();
    }

    // DASHBOARD STATS

    @GetMapping("/stats")

    public Map<String, Object>
    getDashboardStats() {

        return studentService
                .getDashboardStats();
    }

    // DELETE STUDENT

    @DeleteMapping("/{id}")

    public void deleteStudent(

            @PathVariable Long id

    ) {

        studentService
                .deleteStudent(id);
    }

    // UPDATE PAYMENT STATUS

    @PutMapping(
            "/payment/{id}"
    )

    public Student
    updatePaymentStatus(

            @PathVariable Long id,

            @RequestParam String status

    ) {

        return studentService
                .updatePaymentStatus(
                        id,
                        status
                );
    }
}
