package com.app.booking_system.dto;


import com.app.booking_system.entity.Audit;
import com.app.booking_system.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DriverDTO extends Audit {

    private String name;
    private String email;
    private String licenseNumber;
    private Long phoneNumber;
    private Trip trip;

}
