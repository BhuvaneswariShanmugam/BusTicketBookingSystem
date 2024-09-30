package com.app.booking_system.controller;


import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Booking;
import com.app.booking_system.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private  final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PostMapping("/create")
    public ResponseDTO createBooking(@RequestBody Booking booking){
        return this.bookingService.createBooking(booking);
    }

    @GetMapping("/fetch")
    public ResponseDTO getAllBookingDetail(){
        return this.bookingService.getAllBookingDetail();
    }

}
