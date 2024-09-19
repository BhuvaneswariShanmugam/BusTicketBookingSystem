package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public ResponseDTO createOrganization(Organization organization) {
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(organizationRepository.save(organization))
                .statusCode(200)
                .build();

    }
}
