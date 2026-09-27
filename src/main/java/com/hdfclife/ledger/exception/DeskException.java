package com.hdfclife.ledger.exception;

public class DeskException extends RuntimeException {

    private final String errorCode;

    public DeskException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
