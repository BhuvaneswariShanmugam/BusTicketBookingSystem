package com.app.booking_system.controller;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.service.OrganizationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    public OrganizationController(OrganizationService organizationService){
        this.organizationService=organizationService;
    }

    @PostMapping("/")
    public ResponseDTO createOrganization(@RequestBody  Organization organization) {
        return this.organizationService.createOrganization(organization);
    }
}
