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


    @Column(name="number" , nullable = false)
    private Long number;

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="capacity" , nullable = false)
    private Long capacity;

    @Column(name="type" , nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name="trip_id" , nullable = false)
    private Trip trip;


}
