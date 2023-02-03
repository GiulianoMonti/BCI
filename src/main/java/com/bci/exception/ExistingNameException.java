package com.bci.exception;

import java.util.Date;

import lombok.Getter;

@Getter
public class ExistingNameException extends RuntimeException {

    private static final long serialVersionUID = -3534520956070708260L;
    private final Date timestamp;
    private final int code;
    private final String detail;

    public ExistingNameException(Date timestamp, int code, String detail) {
        this.timestamp = timestamp;
        this.code = code;
        this.detail = detail;
    }
}
