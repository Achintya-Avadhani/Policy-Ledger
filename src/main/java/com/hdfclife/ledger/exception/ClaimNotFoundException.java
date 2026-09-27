package com.hdfclife.ledger.exception;

public class ClaimNotFoundException extends DeskException {

    public ClaimNotFoundException(String claimNo) {
        super("CLAIM_NOT_FOUND", "Claim not found: " + claimNo);
    }
}
