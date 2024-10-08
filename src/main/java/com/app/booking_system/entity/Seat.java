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
@Table(name="seat")
public class Seat extends Audit {


    @Column(name="number" ,nullable = false)
    private Long number;

    @ManyToOne
    @JoinColumn(name="bus_id" ,nullable = false)
    private Bus bus;



}
