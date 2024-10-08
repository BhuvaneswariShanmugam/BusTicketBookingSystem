package com.app.booking_system.controller;


import com.app.booking_system.dto.CustomerDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/create")
    public ResponseDTO createCustomer(@RequestBody final CustomerDTO customerDto){
        return this.customerService.createCustomer(customerDto);
    }

    @GetMapping("/fetch")
    public ResponseDTO getAllCustomerDetails(){
        return this.customerService.getAllCustomerDetails();
    }
}
