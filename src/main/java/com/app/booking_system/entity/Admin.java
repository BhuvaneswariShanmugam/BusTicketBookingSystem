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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="role")
    private String role;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;
}
