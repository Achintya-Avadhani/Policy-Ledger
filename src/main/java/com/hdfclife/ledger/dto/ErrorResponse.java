package com.hdfclife.ledger.dto;

import java.time.Instant;
import java.util.List;

public class ErrorResponse {

    private Instant timestamp;
    private String errorCode;
    private String message;
    private List<String> details;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorCode, String message, String path) {
        this(errorCode, message, List.of(), path);
    }

    public ErrorResponse(String errorCode, String message, List<String> details, String path) {
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
