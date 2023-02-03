package com.bci.controller.dto;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.bci.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto extends LoginDto {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;
    private String token;
    private Boolean isActive;
    private String username;
    private String email;
    private String password;

    private Set<PhoneDto> phones;

    public static UserLoginResponseDto userToDtoLogin(UserEntity user) {
        return UserLoginResponseDto.builder().id(user.getId()).created(user.getCreated()).lastLogin(user.getLastLogin())
                .token(user.getToken()).isActive(user.getIsActive()).username(user.getUsername()).email(user.getEmail())
                .password(user.getPassword())
                .phones(user.getPhones().stream().map(PhoneDto::mapping).collect(Collectors.toSet())).build();
    }

}
