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

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="email", nullable = false , unique = true )
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;
}
