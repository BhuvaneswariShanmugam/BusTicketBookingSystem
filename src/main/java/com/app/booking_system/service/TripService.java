package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.dto.TripDTO;
import com.app.booking_system.entity.Admin;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.entity.Trip;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.AdminRepository;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.repository.TripRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class TripService
{

    private final com.app.booking_system.repository.TripRepository tripRepository;
    private final OrganizationRepository organizationRepository;

    public TripService(TripRepository tripRepository , OrganizationRepository organizationRepository){
        this.tripRepository=tripRepository;
        this.organizationRepository=organizationRepository;
    }


    private AdminRepository adminRepository;

    public ResponseDTO createTrip(TripDTO tripDto){

        Organization organization=organizationRepository.findById(tripDto.getOrganization().getId())
                .orElseThrow(()->new badRequestServiceAlartException(Constants.NOT_FOUND));
        Trip obj=Trip.builder()
                .pickupPoint(tripDto.getPickupPoint())
                .destinationPoint(tripDto.getDestinationPoint())
                .pickupTime(tripDto.getPickupTime())
                .reachingTime(tripDto.getReachingTime())
                .expense(tripDto.getExpense())
                .organization(organization)
                .createdAt(tripDto.getCreatedAt())
                .createdBy(tripDto.getCreatedBy())
                .updatedAt(tripDto.getUpdatedAt())
                .updatedBy(tripDto.getUpdatedBy())
                .build();

        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.tripRepository.save(obj))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllTripDetails(){
        return  ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.tripRepository.findAll())
                .statusCode(200)
                .build();
    }
}
