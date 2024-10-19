package com.app.booking_system.dto;

import com.app.booking_system.entity.Customer;
import com.app.booking_system.entity.Seat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO  {

    private Seat seat;
    private Customer customer;
    private Date bookingDateTime;
    private String bookingStatus;
    private Instant travellingDate;


}
