package com.app.booking_system.controller;

import com.app.booking_system.dto.AdminDTO;
import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Admin;
import com.app.booking_system.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping("/create")
    public ResponseDTO createAdmin(@RequestBody AdminDTO adminDto){
        return this.adminService.createAdmin(adminDto);
    }

    @GetMapping("/fetch")
    public ResponseDTO getAllAdminDetails(){
        return this.adminService.getAllAdminDetails();
    }
}
