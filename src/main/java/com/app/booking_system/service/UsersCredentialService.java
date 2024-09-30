package com.app.booking_system.service;

import com.app.booking_system.config.TokenProvider;
import com.app.booking_system.dto.*;
import com.app.booking_system.entity.UsersCredential;
import com.app.booking_system.exception.EmailNotFormattedException;
import com.app.booking_system.exception.InvaildPasswordException;
import com.app.booking_system.repository.UsersCredentialRepository;
import com.app.booking_system.util.Constants;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class UsersCredentialService implements  UserDetailsService {

    private final UsersCredentialRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    @Lazy
    public UsersCredentialService(UsersCredentialRepository usersRepository , AuthenticationManager authenticationManager , TokenProvider tokenProvider){
        this.usersRepository=usersRepository;
        this.authenticationManager=authenticationManager;
        this.tokenProvider=tokenProvider;
    }


    public ResponseDTO signUp(SignupDTO signUp) {

        if(!emailValidation(signUp.getEmail())) {
            throw new EmailNotFormattedException("Email not formatted");
        }

        if(!passwordValidation(signUp.getPassword())) {
            throw new InvaildPasswordException("password should contain more then 8 charactors ");
        }
        UsersCredential user = UsersCredential.builder()
                .email(signUp.getEmail())
                .password(new BCryptPasswordEncoder().encode(signUp.getPassword()))
                .role(signUp.getRole()).build();

        return  ResponseDTO.builder().statusCode(200).data(usersRepository.save(user)).message(Constants.CREATED).build();
    }


    public ResponseDTO signIn(SigninDTO signIn) throws AuthenticationException {

        UserDetails user=usersRepository.findByEmail(signIn.getEmail())
                .orElseThrow(()->  new  UsernameNotFoundException("Email doesn't exist , so please signup"));

        var userNamePassword=new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword());
        var authorizedUser= authenticationManager.authenticate(userNamePassword);
        var accessToken=tokenProvider.generateAccessToken((UsersCredential) authorizedUser.getPrincipal());
        var refreshToken=tokenProvider.generateRefreshToken((UsersCredential) authorizedUser.getPrincipal());

        return ResponseDTO.builder().message(Constants.RETRIEVED).data(new JwtDto(accessToken,refreshToken)).statusCode(200).build();

    }


    public ResponseDTO refreshAccessToken(RefreshTokenDTO request) {
        try {
            String newAccessToken = tokenProvider.refreshAccessToken(request.getRefreshToken());
            String refreshToken = request.setRefreshToken(newAccessToken);
            return ResponseDTO.builder().message(Constants.CREATED).data(refreshToken).statusCode(200).build();
        } catch (Exception e) {

            return ResponseDTO.builder().message(Constants.NOT_FOUND).data("Invalid refresh token").statusCode(401).build();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String userName) {
        UsersCredential user=usersRepository.findByEmail(userName)
                .orElseThrow(()-> new UsernameNotFoundException("user id doesn't exist"));
        return user;

    }


    public boolean emailValidation(String email) {

        if (email == null) {
            return false;
        }
        return Pattern.compile("^[a-z0-9+_.-]+@(gmail|yahoo|outlook|zoho)\\.com$").matcher(email).matches();
    }


    private boolean passwordValidation(String password) {
        String pass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        return Pattern.compile(pass).matcher(password).matches();
    }




}
