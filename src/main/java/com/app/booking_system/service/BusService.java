package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Bus;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.repository.BusRepository;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository ){
        this.busRepository=busRepository;

    }

    public ResponseDTO createBus(Bus bus){
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
