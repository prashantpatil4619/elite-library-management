package com.example.library_management.controller;

import com.example.library_management.dto.SeatBookingRequest;
import com.example.library_management.entity.Seat;
import com.example.library_management.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // GET all seats
    @GetMapping
    public List<Seat> getAllSeats() {

        return seatService.getAllSeats();
    }

    // ADD seat
    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) {

        return seatService.addSeat(seat);
    }

    // BOOK seat
    @PostMapping("/book")
    public String bookSeat(@RequestBody SeatBookingRequest request) {

        return seatService.bookSeat(request);
    }
    @GetMapping("/available")
    public List<Seat> getAvailableSeats() {

        return seatService.getAvailableSeats();
    }
}