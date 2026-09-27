package com.hdfclife.ledger.dto;

import com.hdfclife.ledger.validation.ValidPolicyType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreatePolicyRequest {

    @NotBlank
    @Size(max = 30)
    private String policyNo;

    @NotNull
    private Long customerId;

    @NotBlank
    @ValidPolicyType
    private String productType;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal basePremium;

    private List<String> riderCodes = new ArrayList<>();

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getBasePremium() {
        return basePremium;
    }

    public void setBasePremium(BigDecimal basePremium) {
        this.basePremium = basePremium;
    }

    public List<String> getRiderCodes() {
        return riderCodes;
    }

    public void setRiderCodes(List<String> riderCodes) {
        this.riderCodes = riderCodes != null ? riderCodes : new ArrayList<>();
    }
}
