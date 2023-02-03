package com.bci.utils;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Errors {
    private Set<ErrorDetails> errors;
}