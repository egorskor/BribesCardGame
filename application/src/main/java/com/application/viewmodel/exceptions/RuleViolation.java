package com.application.viewmodel.exceptions;

public class RuleViolation extends RuntimeException{

    public RuleViolation(String message) {
        super(message);
    }
}
