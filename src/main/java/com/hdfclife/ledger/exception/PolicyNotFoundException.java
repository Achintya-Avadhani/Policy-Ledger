package com.hdfclife.ledger.exception;

public class PolicyNotFoundException extends DeskException {

    public PolicyNotFoundException(String policyNo) {
        super("POLICY_NOT_FOUND", "Policy not found: " + policyNo);
    }

    public PolicyNotFoundException(Long policyId) {
        super("POLICY_NOT_FOUND", "Policy not found with id: " + policyId);
    }
}
