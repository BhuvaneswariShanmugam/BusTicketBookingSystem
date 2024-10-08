package com.app.booking_system.dto;

import com.app.booking_system.entity.Organization;
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
public class AdminDTO {


    private String firstName;
    private String lastName;
    private String email;
    private Organization organization;
}
