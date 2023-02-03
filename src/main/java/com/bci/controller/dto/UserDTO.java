package com.bci.controller.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    @Email(message = "{error.format.email}")
    @NotEmpty(message = "{error.empty.email}")
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Pattern(regexp = "((?=.*[A-Z]).*)((\\D*\\d){2,})", message = "" + "{error.wrong.password}")
    @Size(min = 8, max = 12, message = "{error.size.password}")
    private String password;

    private Set<PhoneDto> phones;

}