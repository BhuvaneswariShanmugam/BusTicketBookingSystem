package com.app.booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name="trip")
public class Trip extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="pickup_point")
    private String pickupPoint;

    @Column(name="destination_point")
    private String destinationPoint;

    @CurrentTimestamp
    @Column(name="pickup_time")
    private Date pickupTime;

    @UpdateTimestamp
    @Column(name="reaching_time")
    private Date reachingTime;

    @Column(name="expense")
    private Long expense;

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;
}
