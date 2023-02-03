package com.bci.exception;

import java.util.Date;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.bci.utils.ErrorDetails;
import com.bci.utils.Errors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistingNameException.class)
    public ResponseEntity<Errors> handleExistingNameException(ExistingNameException exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails();
        final Errors errors = new Errors();

        errorDetails.setCode(exception.getCode());
        errorDetails.setDetails(exception.getDetail());
        errorDetails.setTimestamp(exception.getTimestamp());
        errors.setErrors(Set.of(errorDetails));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<Errors> handleExistingEmailException(ExistingEmailException exception,
            WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails();
        final Errors errors = new Errors();
        errorDetails.setCode(exception.getCode());
        errorDetails.setDetails(exception.getDetail());
        errorDetails.setTimestamp(exception.getTimestamp());
        errors.setErrors(Set.of(errorDetails));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        final Errors errors = new Errors();
        errorDetails.setCode(400);
        errorDetails.setDetails(ex.getBindingResult().getFieldError().getDefaultMessage());
        errorDetails.setTimestamp(new Date());
        errors.setErrors(Set.of(errorDetails));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Errors> handleGlobalExceptions(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        final Errors errors = new Errors();
        errorDetails.setCode(500);
        errorDetails.setDetails("an error has occurred please contact system administrator");
        errorDetails.setTimestamp(new Date());
        errors.setErrors(Set.of(errorDetails));

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    public ResponseEntity<Errors> handleBadCredentialsException(BadCredentialsException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        final Errors errors = new Errors();
        errorDetails.setCode(500);
        errorDetails.setDetails(exception.getMessage());
        errorDetails.setTimestamp(new Date());
        errors.setErrors(Set.of(errorDetails));

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
