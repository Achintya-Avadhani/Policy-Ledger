package com.hdfclife.ledger.exception;

public class DuplicatePolicyException extends DeskException {

    public DuplicatePolicyException(String policyNo) {
        super("DUPLICATE_POLICY", "Policy already exists: " + policyNo);
    }
}
