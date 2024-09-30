package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Booking;
import com.app.booking_system.repository.BookingRepository;
import com.app.booking_system.util.Constants;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ResponseDTO createBooking(Booking booking){
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.bookingRepository.save(booking))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllBookingDetail(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.bookingRepository.findAll())
                .statusCode(200)
                .build();
    }
}
