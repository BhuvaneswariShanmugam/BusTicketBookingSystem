package com.app.booking_system.controller;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.dto.TripDTO;
import com.app.booking_system.entity.Trip;
import com.app.booking_system.service.TripService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;
    public TripController(TripService tripService){
        this.tripService=tripService;
    }

    @PostMapping("/create")
    public ResponseDTO createTrip(@RequestBody Trip trip){
        System.err.println(trip.getOrganization().getId());
        int t;
        return this.tripService.createTrip(trip);
    }

    @GetMapping("fetch")
    public ResponseDTO getAllTripDetails(){
        return this.tripService.getAllTripDetails();
    }

}
