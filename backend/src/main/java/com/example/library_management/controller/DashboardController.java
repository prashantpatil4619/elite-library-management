package com.example.library_management.controller;

import com.example.library_management.dto.DashboardResponse;
import com.example.library_management.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboardData() {

        return dashboardService.getDashboardData();
    }
}