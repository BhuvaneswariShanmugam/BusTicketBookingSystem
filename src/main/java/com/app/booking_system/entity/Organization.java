package com.app.booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "organization")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Organization extends Audit {

    @Column(name = "name", nullable=true)
    private String name;

    @Column(name = "phone_number" , nullable=true)
    private Long phoneNumber;

    @Column(name = "email" , nullable=true , unique = true )
    private String email;

    @Column(name = "address1" , nullable=true)
    private String address1;

    @Column(name = "address2" , nullable=true)
    private String address2;

    @Column(name = "city" , nullable=true)
    private String city;

    @Column(name = "state" , nullable=true)
    private String state;

    @Column(name = "is_active" , nullable=true)
    private Boolean isActive;


}
