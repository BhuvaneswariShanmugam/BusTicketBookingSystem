package com.app.booking_system.service;

import com.app.booking_system.config.TokenProvider;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.*;
import com.app.booking_system.repository.BookingRepository;
import com.app.booking_system.repository.SeatRepository;
import com.app.booking_system.repository.TripRepository;
import com.app.booking_system.repository.UserCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BusService busService;
    private final SeatRepository seatRepository;
    private final TokenProvider tokenProvider;
    private  final TripService tripService;
    private final CustomerService customerService;
    private final TripRepository tripRepository;
    private final UserCredentialRepository userCredentialRepository;

    public BookingService(BookingRepository bookingRepository, BusService busService, UserCredentialRepository userCredentialRepository, SeatRepository seatRepository, TokenProvider tokenProvider, TripService tripService, CustomerService customerService , TripRepository tripRepository) {
        this.bookingRepository = bookingRepository;
        this.busService=busService;
        this.seatRepository=seatRepository;
        this.tokenProvider=tokenProvider;
        this.tripService=tripService;
        this.customerService=customerService;
        this.tripRepository=tripRepository;
        this.userCredentialRepository=userCredentialRepository;
    }


    public ResponseDTO getAllBookingDetail(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.bookingRepository.findAll())
                .statusCode(200)
                .build();
    }

    public ResponseDTO createBooking(String pickupPoint, String destinationPoint, String pickupTime, Long busNumber, String busType, List<Long> bookedNoOfSeats, Long perSeatAmount, Long totalAmount, String token) {
        LocalDate pickupDate = LocalDate.parse(pickupTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Instant startOfDay = pickupDate.atStartOfDay(ZoneId.of("Asia/Kolkata")).toInstant();
        Instant endOfDay = pickupDate.plusDays(1).atStartOfDay(ZoneId.of("Asia/Kolkata")).toInstant();

        Trip trip=tripRepository.findByPickupPointAndDestinationPointAndPickupTimeBetween(pickupPoint, destinationPoint, startOfDay, endOfDay);
        System.out.println("Trip found: " + (trip != null ? trip.getId() : "No trip found"));

        Bus bus = busService.findBusId(busNumber, busType);
        if (bus == null) {
            throw new RuntimeException("Bus not found for the gi" +
                    "ven number and type.");
        }

        String userId = tokenProvider.getUserIdFromToken(token);
        System.out.println("Extracted Customer ID: " + userId);
        UserCredential user = userCredentialRepository.findUserById(userId);

        if (user == null) {
            throw new RuntimeException("Customer not found for the given ID.");
        }

        List<Long> alreadyBookedSeat = new ArrayList<>();

        for (Long seatNumber : bookedNoOfSeats) {
            Seat seat = seatRepository.findByNumberAndBusId(seatNumber, bus.getId());
            if (seat==null) {
                alreadyBookedSeat.add(seatNumber); // Track unavailable seats
            }
        }
        Booking booking = Booking.builder()
                .bus(bus)
                .trip(bus.getTrip())
                .user(user)
                .perSeatAmount(perSeatAmount)
                .bookedNoOfSeats(bookedNoOfSeats)
                .totalPrice(totalAmount)
                .build();

        return ResponseDTO.builder()
                .data(Constants.CREATED)
                .data(bookingRepository.save(booking))
                .statusCode(200)
                .build();
    }


}
