package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Seat;
import com.app.booking_system.repository.SeatRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    public SeatService(SeatRepository seatRepository){
        this.seatRepository=seatRepository;
    }

    public ResponseDTO createSeat(Seat seat){
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.seatRepository.save(seat))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllSeatDetail(){
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.seatRepository.findAll())
                .statusCode(200)
                .build();
    }
}
