package com.bci.controller;

import javax.validation.Valid;

import com.bci.service.IAuthenticationService;
import com.bci.controller.dto.LoginDto;
import com.bci.controller.dto.UserDTO;
import com.bci.controller.dto.UserLoginResponseDto;
import com.bci.controller.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private IAuthenticationService authService;

    public UserController(IAuthenticationService authService) {

        this.authService = authService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserDTO request) {
        return new ResponseEntity<>(authService.registerUser(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(@RequestBody LoginDto loginDto) {

        return new ResponseEntity<>(authService.loginUser(loginDto), HttpStatus.OK);
    }

}