package com.bci.service;


import com.bci.controller.dto.LoginDto;
import com.bci.controller.dto.UserDTO;
import com.bci.controller.dto.UserLoginResponseDto;
import com.bci.controller.dto.UserResponseDto;

public interface IAuthenticationService {
    public UserResponseDto registerUser(UserDTO request);

    public UserLoginResponseDto loginUser(LoginDto loginDto);

}
