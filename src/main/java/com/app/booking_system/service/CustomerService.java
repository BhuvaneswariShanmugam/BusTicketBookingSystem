package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Customer;
import com.app.booking_system.entity.UsersCredential;
import com.app.booking_system.repository.CustomerRepository;
import com.app.booking_system.repository.UsersCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UsersCredentialRepository usersRepository;
    public CustomerService(CustomerRepository customerRepository , UsersCredentialRepository usersRepository){
        this.customerRepository=customerRepository;
        this.usersRepository=usersRepository;
    }

    public ResponseDTO createCustomer(Customer customer){
        UsersCredential user=usersRepository.findByEmail(customer.getEmail())
                .orElseThrow(()->  new UsernameNotFoundException("Email doesn't exist , so please signup"));
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.customerRepository.save(customer))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllCustomerDetails(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.customerRepository.findAll())
                .statusCode(200)
                .build();
    }
}
