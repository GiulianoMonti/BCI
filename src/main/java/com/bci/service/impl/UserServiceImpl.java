package com.bci.service.impl;


import com.bci.repository.RoleRepository;
import com.bci.repository.UserRepository;
import com.bci.controller.dto.LoginDto;
import com.bci.controller.dto.UserDTO;
import com.bci.controller.dto.UserLoginResponseDto;
import com.bci.entity.PhoneEntity;
import com.bci.entity.RoleEntity;
import com.bci.entity.UserEntity;
import com.bci.exception.ExistingNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bci.security.JwtTokenProvider;
import com.bci.service.IUserService;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final JwtTokenProvider tokenProvider;

    @Override
    public UserEntity createUser(UserDTO request) {

        RoleEntity roles = roleRepository.findByName("ROLE_ADMIN").orElse(null);

        UserEntity user = UserEntity.builder().username(request.getUsername()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).created(new Date()).lastLogin(new Date())
                .isActive(true)
                .phones(request.getPhones().stream().map(PhoneEntity::fromDtoToEntity).collect(Collectors.toSet()))
                .token(tokenProvider.generateTokens(request.getUsername())).roles(Collections.singleton(roles)).build();
        userRepository.save(user);
        return user;
    }

    @Override
    public UserLoginResponseDto createUserLogin(LoginDto loginDto) {
        UserEntity userFromDatabase = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new ExistingNameException(new Date(), 500,
                        loginDto.getUsername() + " user doesnt exist, please register first"));

        UserLoginResponseDto userForR = UserLoginResponseDto.userToDtoLogin(userFromDatabase);

        userForR.setToken(userFromDatabase.getToken());

        return userForR;
    }

}
