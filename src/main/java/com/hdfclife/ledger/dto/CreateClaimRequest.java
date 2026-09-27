package com.hdfclife.ledger.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateClaimRequest {

    @NotBlank
    @Size(max = 30)
    private String policyNo;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal claimAmount;

    @NotBlank
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Urgency must be one of: LOW, MEDIUM, HIGH")
    private String urgency;

    @NotBlank
    @Size(max = 150)
    private String hospitalName;

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
