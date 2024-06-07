package com.bugcatcorp.app_bugcat_store.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}
