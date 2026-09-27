package com.hdfclife.ledger.exception;

public class InvalidRequestException extends DeskException {

    public InvalidRequestException(String message) {
        super("INVALID_REQUEST", message);
    }
}
