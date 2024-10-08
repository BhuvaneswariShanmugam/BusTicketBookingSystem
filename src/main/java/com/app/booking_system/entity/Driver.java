package com.app.booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name="driver")
public class Driver extends Audit{

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="email" , nullable = false)
    private String email;

    @Column(name="license_number" , nullable = false)
    private String licenseNumber;

    @Column(name="phone_number" , nullable = false)
    private Long phoneNumber;

    @ManyToOne
    @JoinColumn(name="trip_id" , nullable = true)
    private Trip trip;
}
