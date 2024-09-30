package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.entity.Trip;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.AdminRepository;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.repository.TripRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final OrganizationRepository organizationRepository;
    private final AdminRepository adminRepository;

    public TripService(TripRepository tripRepository, OrganizationRepository organizationRepository, AdminRepository adminRepository) {
        this.tripRepository = tripRepository;
        this.organizationRepository = organizationRepository;
        this.adminRepository = adminRepository;
    }

    public ResponseDTO createTrip(Trip trip) {
        // Validate and retrieve the organization
        Organization organization = organizationRepository.findById(trip.getOrganization().getId())
                .orElseThrow(() -> new badRequestServiceAlartException(Constants.ORGANIZATION_NOT_FOUND));

        // Build the Trip object and save it
        Trip savedTrip = Trip.builder()
                .pickupPoint(trip.getPickupPoint())
                .destinationPoint(trip.getDestinationPoint())
                .pickupTime(trip.getPickupTime())
                .reachingTime(trip.getReachingTime())
                .expense(trip.getExpense())
                .organization(organization) // Use the retrieved organization
                .createdBy(trip.getCreatedBy())
                .updatedBy(trip.getUpdatedBy())
                .build();

        tripRepository.save(savedTrip);

        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(savedTrip)
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllTripDetails() {
        List<Trip> trips = tripRepository.findAll(); // Fetch all trips

        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(trips)
                .statusCode(200)
                .build();
    }
}




//
//@Service
//public class TripService
//{
//
//    private final com.app.booking_system.repository.TripRepository tripRepository;
//    private final OrganizationRepository organizationRepository;
//    private  final AdminRepository adminRepository;
//
//
//    public TripService(TripRepository tripRepository , OrganizationRepository organizationRepository , AdminRepository adminRepository){
//        this.tripRepository=tripRepository;
//        this.organizationRepository=organizationRepository;
//        this.adminRepository=adminRepository;
//    }
//
//    public ResponseDTO createTrip(Trip trip){
//        Organization organization = organizationRepository.findById(trip.getOrganization().getId())
//                .orElseThrow(() -> new badRequestServiceAlartException(Constants.ORGANIZATION_NOT_FOUND));
//
//        Trip obj = Trip.builder()
//                .pickupPoint(trip.getPickupPoint())
//                .destinationPoint(trip.getDestinationPoint())
//                .pickupTime(trip.getPickupTime())
//                .reachingTime(trip.getReachingTime())
//                .expense(trip.getExpense())
//                .organization(trip.getOrganization()) // Set the valid organization object
//                .createdBy(trip.getCreatedBy())
//                .updatedBy(trip.getUpdatedBy())
//                .build();
//
//        tripRepository.save(obj);
//
//        return ResponseDTO.builder()
//                .message(Constants.CREATED)
//                .data(obj)
//                .statusCode(200)
//                .build();
//    }
//
//    public ResponseDTO getAllTripDetails(){
//        return  ResponseDTO.builder()
//                .message(Constants.RETRIEVED)
//                .data(this.tripRepository.findAll())
//                .statusCode(200)
//                .build();
//    }
//}
