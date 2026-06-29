package com.example.library_management.service;

import com.example.library_management.dto.SeatBookingRequest;
import com.example.library_management.entity.Seat;
import com.example.library_management.entity.Student;
import com.example.library_management.repository.SeatRepository;
import com.example.library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private StudentRepository studentRepository;

    // GET ALL SEATS
    public List<Seat> getAllSeats() {

        return seatRepository.findAll();
    }

    // ADD SEAT
    public Seat addSeat(Seat seat) {

        return seatRepository.save(seat);
    }

    // BOOK SEAT
    public String bookSeat(SeatBookingRequest request) {

        Optional<Seat> optionalSeat =
                seatRepository.findBySeatNumber(request.getSeatNumber());

        if (optionalSeat.isEmpty()) {

            return "Seat not found";
        }

        Seat seat = optionalSeat.get();

        // CHECK SEAT AVAILABLE
        if (!seat.getAvailable()) {

            return "Seat already booked";
        }

        Optional<Student> optionalStudent =
                studentRepository.findByMobile(request.getMobile());

        if (optionalStudent.isEmpty()) {

            return "Student not found";
        }

        Student student = optionalStudent.get();

        // ASSIGN SEAT
        student.setSeatNumber(seat.getSeatNumber());

        // ADMISSION DATE
        student.setAdmissionDate(LocalDate.now());

        // EXPIRY DATE
        student.setExpiryDate(LocalDate.now().plusMonths(1));

        // MAKE SEAT UNAVAILABLE
        seat.setAvailable(false);

        // SAVE
        studentRepository.save(student);

        seatRepository.save(seat);

        return "Seat booked successfully";
    }

    // AVAILABLE SEATS
    public List<Seat> getAvailableSeats() {

        return seatRepository.findByAvailable(true);
    }
}