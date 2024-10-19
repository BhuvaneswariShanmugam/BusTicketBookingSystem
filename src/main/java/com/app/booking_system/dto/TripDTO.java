package com.app.booking_system.dto;

import com.app.booking_system.entity.Organization;
import lombok.*;



import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripDTO {

    private String pickupPoint;
    private String destinationPoint;
    private Instant pickupTime;
    private Instant reachingTime;
    private Long expense;
    private Organization organization;
}
