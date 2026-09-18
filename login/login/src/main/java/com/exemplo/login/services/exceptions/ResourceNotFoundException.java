package com.exemplo.login.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("The user with the ID " + id + " could not be found.");
    }
}
