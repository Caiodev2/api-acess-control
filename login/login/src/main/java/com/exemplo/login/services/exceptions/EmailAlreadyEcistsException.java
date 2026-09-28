package com.exemplo.login.services.exceptions;

public class EmailAlreadyEcistsException extends RuntimeException {
    public EmailAlreadyEcistsException(String email) {
        super("O email " + email + " já está cadastrado.");
    }
}
