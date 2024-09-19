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


    private String name;
    private String role;
    private String email;
    private String password;
    private Organization organization;
}
