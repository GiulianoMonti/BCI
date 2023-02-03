package com.bci.utils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDetails {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private int code;
    private String details;

    public ErrorDetails(Date timestamp, int code, String details) {
        this.timestamp = timestamp;
        this.code = code;
        this.details = details;
    }

    public ErrorDetails(String details) {

        this.details = details;
    }

}
