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
@Table(name="admin")
public class Admin extends Audit{

    @Column(name="first_name" , nullable = false)
    private String firstName;

    @Column(name="last_name" , nullable = false)
    private String lastName;

    @Column(name="email", nullable = false , unique = true )
    private String email;

    @ManyToOne
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;
}
