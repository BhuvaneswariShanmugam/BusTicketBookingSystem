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
public class BusDTO extends Audit {


    private Long number;
    private String name;
    private Long capacity;
    private String type;
    private Trip trip;


}
