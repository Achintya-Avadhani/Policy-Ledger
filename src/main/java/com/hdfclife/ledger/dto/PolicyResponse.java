package com.hdfclife.ledger.dto;

import java.math.BigDecimal;
import java.util.List;

public class PolicyResponse {

    private Long id;
    private String policyNo;
    private Long customerId;
    private String customerName;
    private String productType;
    private BigDecimal basePremium;
    private String status;
    private List<String> riderCodes;

    public PolicyResponse() {
    }

    public PolicyResponse(
            Long id,
            String policyNo,
            Long customerId,
            String customerName,
            String productType,
            BigDecimal basePremium,
            String status,
            List<String> riderCodes) {
        this.id = id;
        this.policyNo = policyNo;
        this.customerId = customerId;
        this.customerName = customerName;
        this.productType = productType;
        this.basePremium = basePremium;
        this.status = status;
        this.riderCodes = riderCodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRiderCodes() {
        return riderCodes;
    }

    public void setRiderCodes(List<String> riderCodes) {
        this.riderCodes = riderCodes;
    }
}
