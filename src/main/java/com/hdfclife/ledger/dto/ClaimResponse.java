package com.hdfclife.ledger.dto;

import java.math.BigDecimal;

public class ClaimResponse {

    private Long id;
    private String claimNo;
    private String policyNo;
    private BigDecimal claimAmount;
    private String urgency;
    private String hospitalName;

    public ClaimResponse() {
    }

    public ClaimResponse(
            Long id,
            String claimNo,
            String policyNo,
            BigDecimal claimAmount,
            String urgency,
            String hospitalName) {
        this.id = id;
        this.claimNo = claimNo;
        this.policyNo = policyNo;
        this.claimAmount = claimAmount;
        this.urgency = urgency;
        this.hospitalName = hospitalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
