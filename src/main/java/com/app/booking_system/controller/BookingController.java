package com.app.booking_system.controller;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseDTO createBooking(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam String pickupPoint,
            @RequestParam String destinationPoint,
            @RequestParam String pickupTime,
            @RequestParam Long busNumber,
            @RequestParam String busType,
            @RequestParam List<Long> bookedNoOfSeats,
            @RequestParam Long perSeatAmount,
            @RequestParam Long totalAmount) {

        String token = authorizationHeader.substring(7);
        System.err.println("Received parameters: " + pickupPoint + ", " + destinationPoint + ", " + pickupTime + ", " + busNumber + ", " + busType + ", " + bookedNoOfSeats + ", " + perSeatAmount + ", " + totalAmount);

        return this.bookingService.createBooking(pickupPoint, destinationPoint, String.valueOf(pickupTime), busNumber, busType, bookedNoOfSeats, perSeatAmount, totalAmount,token);
    }

//    @PostMapping("/delete")
//    public ResponseDTO deleteBooking(){
//        return this.bookingService.deleteBooking();
//    }

    @GetMapping("/fetch")
    public ResponseDTO getAllBookingDetail() {
        return this.bookingService.getAllBookingDetail();
    }
}
