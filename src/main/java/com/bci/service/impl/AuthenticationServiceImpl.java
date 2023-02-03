package com.bci.service.impl;

import com.bci.controller.dto.LoginDto;
import com.bci.controller.dto.UserDTO;
import com.bci.controller.dto.UserLoginResponseDto;
import com.bci.controller.dto.UserResponseDto;
import com.bci.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import com.bci.service.IAuthenticationService;
import com.bci.service.IUserService;
import com.bci.utils.UserChecking;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserService userService;

    private final AuthenticationManager authenticationManager;
    
    private final UserChecking checkUsers;


    @Override
    public UserResponseDto registerUser(UserDTO request) {

        checkUsers.checkUserEmail(request.getUsername(),request.getEmail());
        
        UserEntity user = userService.createUser(request);

        return UserResponseDto.userToDto(user);
    }

    @Override
    public UserLoginResponseDto loginUser(LoginDto loginDto) {
        
        checkUsers.checkUserForLogin(loginDto.getUsername());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return userService.createUserLogin(loginDto);

    }

}
