package com.bci.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExistingEmailException extends RuntimeException {

    private static final long serialVersionUID = -3066446381099045270L;
    private final Date timestamp;
    private final int code;
    private final String detail;

}
