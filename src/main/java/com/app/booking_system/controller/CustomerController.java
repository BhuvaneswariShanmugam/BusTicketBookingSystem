package com.app.booking_system.controller;


import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Customer;
import com.app.booking_system.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/create")
    public ResponseDTO createCustomer(Customer customer){
        return this.customerService.createCustomer(customer);
    }

    @GetMapping("/fetch")
    public ResponseDTO getAllCustomerDetails(){
        return this.customerService.getAllCustomerDetails();
    }
}
