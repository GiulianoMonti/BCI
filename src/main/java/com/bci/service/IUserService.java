package com.bci.service;


import com.bci.controller.dto.LoginDto;
import com.bci.controller.dto.UserDTO;
import com.bci.controller.dto.UserLoginResponseDto;
import com.bci.entity.UserEntity;

public interface IUserService {

    public UserEntity createUser(UserDTO request);

    public UserLoginResponseDto createUserLogin(LoginDto loginDto);

}
