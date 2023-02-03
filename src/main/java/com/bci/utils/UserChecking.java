package com.bci.utils;

import java.util.Date;
import java.util.Locale;

import com.bci.exception.ExistingEmailException;
import com.bci.exception.ExistingNameException;
import com.bci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;



import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserChecking {

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    @Value("error.repeated.user")
    private String repeatedUser;

    @Value("error.repeated.email")
    private String repeatedEmail;

    @Value("error.nonexistent.user")
    private String notAddedUser;

    public void checkUserEmail(String username, String email) {

        if (userRepository.existsByUsername(username)) {
            throw new ExistingNameException(new Date(), 400,
                    username + messageSource.getMessage(repeatedUser, null, Locale.getDefault()));
        }

        if (userRepository.existsByEmail(email)) {
            throw new ExistingEmailException(new Date(), 400,
                    email + messageSource.getMessage(repeatedEmail, null, Locale.getDefault()));
        }
    }

    public void checkUserForLogin(String username) {
        if (userRepository.findByUsername(username).isEmpty()) {
            throw new ExistingNameException(new Date(), 400,
                    username + messageSource.getMessage(notAddedUser, null, Locale.getDefault()));

        }
    }

}
