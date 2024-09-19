package com.app.booking_system.controller;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.dto.SigninDTO;
import com.app.booking_system.dto.SignupDTO;
import com.app.booking_system.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UsersController {

    private final UsersService userService;

    public UsersController(UsersService userService){
        this.userService=userService;
    }

    @PostMapping("/sign-up")
    public ResponseDTO signUp(@RequestBody SignupDTO signupDto){
        return this.userService.signUp(signupDto);
    }

    @PostMapping("/sign-in")
    public ResponseDTO signIn(@RequestBody SigninDTO signinDto){
        return this.userService.signIn(signinDto);
    }
}
