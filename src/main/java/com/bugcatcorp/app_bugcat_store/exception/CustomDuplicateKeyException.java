package com.bugcatcorp.app_bugcat_store.exception;

public class CustomDuplicateKeyException extends RuntimeException {
    public CustomDuplicateKeyException(String message) {
        super(message);
    }
}
