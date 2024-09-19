package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Admin;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.AdminRepository;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final OrganizationRepository organizationRepository;


    public AdminService(AdminRepository adminRepository ,OrganizationRepository organizationRepository){
        this.adminRepository=adminRepository;
        this.organizationRepository=organizationRepository;

    }

//    public ResponseDTO createAdmin(Admin admin){
//        return ResponseDTO.builder()
//                .message(Constants.CREATED)
//                .data(this.adminRepository.save(admin))
//                .statusCode(200)
//                .build();
//    }

    public ResponseDTO createAdmin(Admin admin){
        Organization organization = organizationRepository.findById(admin.getOrganization().getId())
                          .orElseThrow(()-> new badRequestServiceAlartException(Constants.NOT_FOUND));
        Admin obj=Admin.builder()
                .id(admin.getId())
                .name(admin.getName())
                .role(admin.getRole())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .organization(admin.getOrganization())
                .createdAt(admin.getCreatedAt())
                .createdBy(admin.getCreatedBy())
                .updatedAt(admin.getUpdatedAt())
                .updatedBy(admin.getUpdatedBy())
                .build();

        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.adminRepository.save(obj))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllAdminDetails(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.adminRepository.findAll())
                .statusCode(200)
                .build();
    }
}
