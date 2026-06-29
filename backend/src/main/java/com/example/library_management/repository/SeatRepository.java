package com.example.library_management.repository;

import com.example.library_management.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySeatNumber(String seatNumber);

    List<Seat> findByAvailable(Boolean available);

    Long countByAvailable(Boolean available);
}