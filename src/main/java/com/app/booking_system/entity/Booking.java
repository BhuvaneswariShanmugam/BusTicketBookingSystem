package com.app.booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CurrentTimestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name="booking")
public class Booking  extends  Audit{

    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @CurrentTimestamp
    @Column(name="booking_date_time")
    private Date bookingDateTime;

    @Column(name="booking_status")
    private String booking_status;

    @Column(name="travelling_date")
    private Instant travellingDate; // "2024-10-01T00:00:00Z"
}
