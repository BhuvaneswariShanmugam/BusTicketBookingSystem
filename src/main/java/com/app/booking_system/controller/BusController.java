package com.app.booking_system.controller;

import com.app.booking_system.dto.BusDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.service.BusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;
    public BusController(BusService busService){
        this.busService=busService;
    }

    @PostMapping("/create")
    public ResponseDTO createBus(@RequestBody BusDTO busDto){
        return this.busService.createBus(busDto);
    }

    @GetMapping("/fetch")
    public ResponseDTO getAllBusDetails(){
        return this.busService.getAllBusDetails();
    }
}
