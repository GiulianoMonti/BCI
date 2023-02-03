package com.bci.controller.dto;

import java.util.Date;
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
public class UserResponseDto extends UserDTO {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private String token;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;
    private Boolean isActive;

    public static UserResponseDto userToDto(UserEntity user) {
        return UserResponseDto.builder().id(user.getId()).token(user.getToken()).username(user.getUsername())
                .email(user.getEmail()).password(user.getPassword())
                .phones(user.getPhones().stream().map(PhoneDto::mapping).collect(Collectors.toSet()))
                .created(user.getCreated()).lastLogin(user.getLastLogin()).token(user.getToken())
                .isActive(user.getIsActive()).build();

    }

}