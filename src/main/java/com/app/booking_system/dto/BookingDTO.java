package com.app.booking_system.dto;

import com.app.booking_system.entity.Audit;
import com.app.booking_system.entity.Customer;
import com.app.booking_system.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookingDTO extends Audit {

    private Seat seat;
    private Customer customer;
    private Date bookingDateTime;
    private String bookingStatus;
    private Instant travellingDate;


}
