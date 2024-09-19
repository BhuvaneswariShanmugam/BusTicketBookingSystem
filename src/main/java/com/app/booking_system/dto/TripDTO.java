package com.app.booking_system.dto;

import com.app.booking_system.entity.Audit;
import com.app.booking_system.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TripDTO extends Audit {

    private String pickupPoint;
    private String destinationPoint;
    private Date pickupTime;
    private Date reachingTime;
    private Long expense;
    private Organization organization;
}
