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
@Table(name="feedback")
public class Feedback  extends  Audit{

    @ManyToOne
    @JoinColumn(name="customer_id")
    private  Customer customer;

    @Column(name="feedback")
    private String feedback;
}
