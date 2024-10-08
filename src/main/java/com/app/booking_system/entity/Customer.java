package com.app.booking_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Customer extends  Audit {


    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = true)
    private String lastName;

    @Column(name="gender" , nullable = false)
    private String gender;

    @Column(name = "email", unique = true , nullable = false)
    private String email;

    @Column(name = "phone_number" , nullable = false)
    private Long phoneNumber;

    @ManyToOne
    @JoinColumn(name="user_credential_id" , nullable = false)
    private UserCredential userCredential;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;



}