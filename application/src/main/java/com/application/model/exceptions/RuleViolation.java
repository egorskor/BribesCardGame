package com.application.model.exceptions;

public class RuleViolation extends RuntimeException{

    public RuleViolation(String message) {
        super(message);
    }
}
