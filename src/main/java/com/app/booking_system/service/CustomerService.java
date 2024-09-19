package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Customer;
import com.app.booking_system.entity.Users;
import com.app.booking_system.repository.CustomerRepository;
import com.app.booking_system.repository.UsersRepository;
import com.app.booking_system.util.Constants;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UsersRepository userRepository;
    public CustomerService(CustomerRepository customerRepository , UsersRepository userRepository){
        this.customerRepository=customerRepository;
        this.userRepository=userRepository;
    }

    public ResponseDTO createCustomer(Customer customer){
        Users user=userRepository.findById(customer.getUser().getId())
                .orElseThrow(()->  new UsernameNotFoundException("User Id doesn't exist , so please signup"));
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
