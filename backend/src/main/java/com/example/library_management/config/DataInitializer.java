package com.example.library_management.config;

import com.example.library_management.entity.Seat;
import com.example.library_management.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(SeatRepository seatRepository) {

        return args -> {

            if (seatRepository.count() == 0) {

                // A1-A40 => 1500
                for (int i = 1; i <= 40; i++) {

                    Seat seat = new Seat();

                    seat.setSeatNumber("A" + i);
                    seat.setPrice(1500);
                    seat.setAvailable(true);

                    seatRepository.save(seat);
                }

                // B1-B40 => 1200
                for (int i = 1; i <= 40; i++) {

                    Seat seat = new Seat();

                    seat.setSeatNumber("B" + i);
                    seat.setPrice(1200);
                    seat.setAvailable(true);

                    seatRepository.save(seat);
                }

                System.out.println("80 Seats Added Successfully");
            }
        };
    }
}