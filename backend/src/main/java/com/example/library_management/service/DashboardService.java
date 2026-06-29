package com.example.library_management.service;

import com.example.library_management.dto.DashboardResponse;
import com.example.library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    public DashboardResponse getDashboardData() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalStudents(studentRepository.count());

        long premiumBooked = studentRepository.findAll()
                .stream()
                .filter(s -> s.getFees() != null && s.getFees() == 1500)
                .count();

        long normalBooked = studentRepository.findAll()
                .stream()
                .filter(s -> s.getFees() != null && s.getFees() == 1200)
                .count();

        int revenue = studentRepository.findAll()
                .stream()
                .filter(s -> "PAID".equalsIgnoreCase(s.getPaymentStatus()))
                .mapToInt(s -> s.getFees())
                .sum();

        response.setPremiumBooked(premiumBooked);
        response.setPremiumRemaining(40L - premiumBooked);

        response.setNormalBooked(normalBooked);
        response.setNormalRemaining(40L - normalBooked);

        response.setRevenue(revenue);

        return response;
    }
}
