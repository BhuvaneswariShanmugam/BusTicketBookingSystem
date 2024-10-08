package com.app.booking_system.service;

import com.app.booking_system.config.UserAuthHelper;
import com.app.booking_system.dto.BusDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Bus;
import com.app.booking_system.repository.BusRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository ){
        this.busRepository=busRepository;

    }

    public ResponseDTO createBus(BusDTO busDto){

        String userId = UserAuthHelper.getCurrentUserId();

        Bus bus= Bus.builder()
                .name(busDto.getName())
                .number(busDto.getNumber())
                .capacity(busDto.getCapacity())
                .type(busDto.getType())
                .trip(busDto.getTrip())
                .createdBy(userId)
                .updatedBy(userId)
                .build();
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.busRepository.save(bus))
                .statusCode(200)
                .build();

    }

    public ResponseDTO getAllBusDetails(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.busRepository.findAll())
                .statusCode(200)
                .build();
    }
}
