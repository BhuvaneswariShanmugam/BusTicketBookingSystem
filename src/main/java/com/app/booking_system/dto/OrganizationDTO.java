package com.app.booking_system.dto;

import com.app.booking_system.entity.Audit;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrganizationDTO extends Audit {

    private String name;
    private Long phoneNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private Boolean is_active;
}
