package com.app.booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name="trip")
public class Trip extends Audit{

    @Column(name="pickup_point" , nullable = false)
    private String pickupPoint;

    @Column(name="destination_point" , nullable = false)
    private String destinationPoint;

    @Column(name="pickup_time" , nullable = false)
    private Instant pickupTime;     //2024-09-26T10:00:00Z

    @Column(name="reaching_time" , nullable = false)
    private Instant reachingTime;

    @Column(name="expense" , nullable = false)
    private Long expense;

    @ManyToOne
    @JoinColumn(name="organization_id" , nullable = false)
    private Organization organization;
}
