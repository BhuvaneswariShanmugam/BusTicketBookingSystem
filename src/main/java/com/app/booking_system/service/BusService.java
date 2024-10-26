package com.app.booking_system.service;

import com.app.booking_system.dto.BusDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Bus;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.BusRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository ){
        this.busRepository=busRepository;

    }

    public ResponseDTO createBus(BusDTO busDTO){

        Bus bus= Bus.builder()
                .number(busDTO.getNumber())
                .capacity(busDTO.getCapacity())
                .type(busDTO.getType())
                .trip(busDTO.getTrip())
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

    public ResponseDTO getBusById(@PathVariable String id){
       Bus bus=busRepository.findById(id).orElseThrow(()-> new badRequestServiceAlartException("Bus doesn't exist"));
       return ResponseDTO.builder()
               .statusCode(200)
               .data(bus)
               .message(Constants.RETRIEVED)
               .build();
    }


    public ResponseDTO updateBus(String id, BusDTO busDTO) {
        Bus bus=busRepository.findById(id).orElseThrow(()->new badRequestServiceAlartException("Bus does not exist"));
        if (busDTO.getNumber() != null) {
            bus.setNumber(busDTO.getNumber());
        }
        if (busDTO.getCapacity() != null) {
            bus.setCapacity(busDTO.getCapacity());
        }
        if (busDTO.getType() != null) {
            bus.setType(busDTO.getType());
        }
        if (busDTO.getTrip() != null) {
            bus.setTrip(busDTO.getTrip());
        }

        busRepository.save(bus);
        return ResponseDTO.builder()
                .message(Constants.UPDATED)
                .data(bus)
                .statusCode(200)
                .build();
    }
}
