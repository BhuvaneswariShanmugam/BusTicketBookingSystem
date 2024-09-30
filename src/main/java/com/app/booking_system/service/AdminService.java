package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Admin;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.entity.UsersCredential;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.AdminRepository;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.repository.UsersCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final OrganizationRepository organizationRepository;
    private final UsersCredentialRepository usersRepository;


    public AdminService(AdminRepository adminRepository ,OrganizationRepository organizationRepository , UsersCredentialRepository usersRepository){
        this.adminRepository=adminRepository;
        this.organizationRepository=organizationRepository;
        this.usersRepository=usersRepository;

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
                          .orElseThrow(()-> new badRequestServiceAlartException(Constants.ORGANIZATION_NOT_FOUND));
        UsersCredential user=usersRepository.findByEmail(admin.getEmail())
                .orElseThrow(()-> new badRequestServiceAlartException(Constants.EMAIL_NOT_EXIST));

        if(user!=null){
            return ResponseDTO.builder()
                    .message(Constants.CREATED)
                    .data(this.adminRepository.save(admin))
                    .statusCode(200)
                    .build();
        }
        return ResponseDTO.builder()
                .message(Constants.USER_NOT_FOUND)
                .statusCode(200)
                .build();
//        Admin obj=Admin.builder()
//                .id(admin.getId())
//                .name(admin.getName())
//                .email(admin.getEmail())
//                .password(admin.getPassword())
//                .organization(admin.getOrganization())
//                .createdAt(admin.getCreatedAt())
//                .createdBy(admin.getCreatedBy())
//                .updatedAt(admin.getUpdatedAt())
//                .updatedBy(admin.getUpdatedBy())
//                .build();

//        Organization organization1 = new Organization();
//        organization1.setId(admin.getId());
//        organization1.setCreatedBy("achudha");

//        return ResponseDTO.builder()
//                .message(Constants.CREATED)
//                .data(this.adminRepository.save(obj))
//                .statusCode(200)
//                .build();
    }

    public ResponseDTO getAllAdminDetails(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.adminRepository.findAll())
                .statusCode(200)
                .build();
    }
}
