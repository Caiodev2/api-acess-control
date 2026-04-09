package com.exemplo.login.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Não foi possível deleter o user" + id);
    }
}
