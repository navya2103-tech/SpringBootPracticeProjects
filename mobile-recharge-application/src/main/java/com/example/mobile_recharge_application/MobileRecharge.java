package com.example.mobile_recharge_application;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MobileRecharge {

    private int rechargeId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$",
             message = "Enter a valid 10-digit Indian mobile number")
    private String mobileNumber;

    @NotBlank(message = "Operator is required")
    private String operator;

    @Min(value = 10, message = "Recharge amount must be at least 10")
    private double amount;

    @NotBlank(message = "Plan type is required")
    private String planType;

    public MobileRecharge() {
    }

    public int getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(int rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}