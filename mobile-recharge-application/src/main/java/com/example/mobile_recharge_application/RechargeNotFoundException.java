package com.example.mobile_recharge_application;

public class RechargeNotFoundException extends RuntimeException {

    public RechargeNotFoundException(String message) {
        super(message);
    }
}