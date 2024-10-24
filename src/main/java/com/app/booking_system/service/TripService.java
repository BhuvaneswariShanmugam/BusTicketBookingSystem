package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.dto.TripDTO;
import com.app.booking_system.entity.Trip;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.repository.TripRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final OrganizationRepository organizationRepository;


    public TripService(TripRepository tripRepository, OrganizationRepository organizationRepository) {
        this.tripRepository = tripRepository;
        this.organizationRepository = organizationRepository;

    }

    public ResponseDTO createTrip(TripDTO tripDTO) {
//        Organization organization = organizationRepository.findById(tripDTO.getOrganization().getId())
//                .orElseThrow(() -> new badRequestServiceAlartException(Constants.ORGANIZATION_NOT_FOUND));

        Trip savedTrip = Trip.builder()
                .destinationPoint(tripDTO.getDestinationPoint())
                .pickupPoint(tripDTO.getPickupPoint())
                .pickupTime(tripDTO.getPickupTime())
                .reachingTime(tripDTO.getReachingTime())
                .expense(tripDTO.getExpense())
                .build();

        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.tripRepository.save(savedTrip))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllTripDetails() {
        List<Trip> trips = tripRepository.findAll();

        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(trips)
                .statusCode(200)
                .build();
    }

    public boolean existsTrip(String pickupPoint, String destinationPoint, String pickupDateStr) {
        // Parse the pickupDate from String to LocalDate
        LocalDate pickupDate = LocalDate.parse(pickupDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Convert the LocalDate to Instant (start and end of the day)
        Instant startOfDay = pickupDate.atStartOfDay(ZoneId.of("Asia/Kolkata")).toInstant();
        Instant endOfDay = pickupDate.plusDays(1).atStartOfDay(ZoneId.of("Asia/Kolkata")).toInstant();

        // Query the repository to check if any trip exists in the given time range
        return tripRepository.existsByPickupPointAndDestinationPointAndPickupTimeBetween(
                pickupPoint, destinationPoint, startOfDay, endOfDay
        );
    }

    private TripDTO convertToTripDTO(Trip trip) {
        return TripDTO.builder()
                .pickupPoint(trip.getPickupPoint())
                .destinationPoint(trip.getDestinationPoint())
                .pickupTime(trip.getPickupTime())  // Convert Instant to String
                .reachingTime(trip.getReachingTime())  // Convert Instant to String
                .expense(trip.getExpense())
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
