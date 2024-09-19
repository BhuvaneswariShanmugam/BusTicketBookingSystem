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
public class Bus extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="number")
    private Long number;

    @Column(name="name")
    private String name;

    @Column(name="capacity")
    private Long capacity;


    @Column(name="type")
    private String type;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;


}
