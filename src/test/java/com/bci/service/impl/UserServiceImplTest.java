package com.bci.service.impl;

import com.bci.controller.dto.LoginDto;
import com.bci.controller.dto.PhoneDto;
import com.bci.controller.dto.UserDTO;
import com.bci.controller.dto.UserLoginResponseDto;
import com.bci.entity.PhoneEntity;
import com.bci.entity.RoleEntity;
import com.bci.entity.UserEntity;
import com.bci.exception.ExistingNameException;
import com.bci.repository.RoleRepository;
import com.bci.repository.UserRepository;
import com.bci.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtTokenProvider tokenProvider;

    UserEntity user;
    UserDTO userRequest;
    LoginDto loginDto;

    @BeforeEach
    void setUp() {
        PhoneEntity phones = new PhoneEntity();
        RoleEntity roles = new RoleEntity();
        roles.setName("ROLE_ADMIN");
        roles.setId(1L);
        phones.setCityCode(123);
        phones.setCountryCode("ABC");
        phones.setId(1L);
        phones.setNumber(1234);

        user = UserEntity.builder().id("string").email("global@logic.co").password("globaLLaog14").username("global")
                .token("123").phones(Collections.singleton(phones)).roles(Collections.singleton(roles))
                .lastLogin(new Date()).created(new Date()).isActive(true).build();

        userRequest = UserDTO.builder().email("global@logic.com").password("globaLLaog14").username("global")
                .phones(user.getPhones()
                        .stream().map(PhoneDto::mapping).collect(Collectors.toSet())).build();

        loginDto = LoginDto.builder().username("global").password("globaLLaog14").build();
    }

    @Test
    void givenUserObject_whenSaveUser_thenReturnUserObject() {

//        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));
        given(userRepository.save(any(UserEntity.class))).willReturn(user);

        System.out.println(userRepository);
        System.out.println(userService);

        UserEntity savedUser = userService.createUser(userRequest);

        assertThat(savedUser).isNotNull();

    }

    @Test
    void givenUserObject_whenLoginUser_thenReturnException() {

        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        LoginDto userForException = new LoginDto();


        org.junit.jupiter.api.Assertions.assertThrows(ExistingNameException.class, () -> {
            userService.createUserLogin(userForException);
        });
    }

    @Test
    void givenUserObject_whenLoginUser_thenReturnUserObject() {

        given(userRepository.findByUsername(loginDto.getUsername())).willReturn(Optional.of(user));

        UserLoginResponseDto savedUser = userService.createUserLogin(loginDto);

        assertThat(savedUser).isNotNull();

    }

}
