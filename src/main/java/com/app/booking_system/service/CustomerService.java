package com.app.booking_system.service;

import com.app.booking_system.config.UserAuthHelper;
import com.app.booking_system.dto.CustomerDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Customer;
import com.app.booking_system.entity.UserCredential;
import com.app.booking_system.exception.badRequestServiceAlartException;
import com.app.booking_system.repository.CustomerRepository;
import com.app.booking_system.repository.UserCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserCredentialRepository userCredentialRepository;
    public CustomerService(CustomerRepository customerRepository , UserCredentialRepository userCredentialRepository){
        this.customerRepository=customerRepository;
        this.userCredentialRepository=userCredentialRepository;
    }

    public ResponseDTO createCustomer(CustomerDTO customerDto){

        String userId = UserAuthHelper.getCurrentUserId();

        UserCredential user=userCredentialRepository.findByEmail(customerDto.getEmail())
                .orElseThrow(()->  new UsernameNotFoundException("User doesn't exist , so please signup"));
        if(!customerDto.getEmail().equals(user.getEmail())){
            throw new badRequestServiceAlartException("entered mailId and provided mailId should be same ");
        }
        Customer customer= Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .trip(customerDto.getTrip())
                .gender(customerDto.getGender())
                .phoneNumber(customerDto.getPhoneNumber())
                .userCredential(customerDto.getUserCredential())
                .createdBy(userId)
                .updatedBy(userId)
                .build();
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
