package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Driver;
import com.app.booking_system.entity.UsersCredential;
import com.app.booking_system.repository.DriverRepository;
import com.app.booking_system.repository.UsersCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final UsersCredentialRepository usersRepository;
    public DriverService(DriverRepository driverRepository , UsersCredentialRepository usersRepository){
        this.driverRepository=driverRepository;
        this.usersRepository=usersRepository;
    }

    public ResponseDTO createDriver(Driver driver){
        UsersCredential user=usersRepository.findByEmail(driver.getEmail())
                .orElseThrow(()->  new UsernameNotFoundException("Email doesn't exist , so please signup"));
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.driverRepository.save(driver))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllDriverDetail(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.driverRepository.findAll())
                .statusCode(200)
                .build();
    }
}
