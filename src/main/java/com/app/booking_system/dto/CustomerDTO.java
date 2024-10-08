package com.app.booking_system.dto;

import com.app.booking_system.entity.Audit;
import com.app.booking_system.entity.Trip;
import com.app.booking_system.entity.UserCredential;
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
public class CustomerDTO extends Audit {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private Long phoneNumber;
    private UserCredential userCredential;
    private Trip trip;
}
