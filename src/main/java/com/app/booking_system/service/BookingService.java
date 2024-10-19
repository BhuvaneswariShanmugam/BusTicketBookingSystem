package com.app.booking_system.service;

import com.app.booking_system.config.UserAuthHelper;
import com.app.booking_system.dto.BookingDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Booking;
import com.app.booking_system.repository.BookingRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ResponseDTO createBooking(BookingDTO bookingDto){

        Booking booking= Booking.builder()
                .seat(bookingDto.getSeat())
                .booking_status(bookingDto.getBookingStatus())
                .bookingDateTime(bookingDto.getBookingDateTime())
                .customer(bookingDto.getCustomer())
                .travellingDate(bookingDto.getTravellingDate())
                .build();
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
