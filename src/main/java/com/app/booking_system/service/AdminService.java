package com.app.booking_system.service;

import com.app.booking_system.config.UserAuthHelper;
import com.app.booking_system.dto.AdminDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Admin;
import com.app.booking_system.entity.Organization;
import com.app.booking_system.entity.UserCredential;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.AdminRepository;
import com.app.booking_system.repository.OrganizationRepository;
import com.app.booking_system.repository.UserCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final OrganizationRepository organizationRepository;
    private final UserCredentialRepository userCredentialRepository;


    public AdminService(AdminRepository adminRepository, OrganizationRepository organizationRepository, UserCredentialRepository userCredentialRepository) {
        this.adminRepository = adminRepository;
        this.organizationRepository = organizationRepository;
        this.userCredentialRepository = userCredentialRepository;

    }

    public ResponseDTO createAdmin(AdminDTO adminDto) {

        String userId = UserAuthHelper.getCurrentUserId();

        Organization organization = organizationRepository.findById(adminDto.getOrganization().getId())
                .orElseThrow(() -> new badRequestServiceAlartException(Constants.ORGANIZATION_NOT_FOUND));

        UserCredential user = userCredentialRepository.findByEmail(adminDto.getEmail())
                .orElseThrow(() -> new badRequestServiceAlartException("User doesn't exist"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        if (user.getEmail().equals(adminDto.getEmail())) {
            Admin admin = Admin.builder()
                    .firstName(adminDto.getFirstName())
                    .lastName(adminDto.getLastName())
                    .email(adminDto.getEmail())
                    .organization(organization)
                    .createdBy(userId)
                    .updatedBy(userId)
                    .build();

            adminRepository.save(admin);

            return ResponseDTO.builder()
                    .message(Constants.CREATED)
                    .data(admin)
                    .statusCode(200)
                    .build();
        }

        return ResponseDTO.builder()
                .message("User mailId and entered mailId should be the same")
                .statusCode(400)
                .build();
    }

        public ResponseDTO getAllAdminDetails () {
            return ResponseDTO.builder()
                    .message(Constants.RETRIEVED)
                    .data(this.adminRepository.findAll())
                    .statusCode(200)
                    .build();
        }



}


