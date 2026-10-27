package com.example.mobile_recharge_application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MobileRechargeService {

    private final List<MobileRecharge> rechargeList = new ArrayList<>();
    private int nextId = 1;

    public MobileRecharge addRecharge(MobileRecharge recharge) {
        recharge.setRechargeId(nextId++);
        rechargeList.add(recharge);
        return recharge;
    }

    public List<MobileRecharge> getAllRecharges() {
        return rechargeList;
    }

    public MobileRecharge getRechargeById(int id) {
        return rechargeList.stream()
                .filter(recharge -> recharge.getRechargeId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteRecharge(int id) {
        return rechargeList.removeIf(
                recharge -> recharge.getRechargeId() == id);
    }

    public long countRecharges() {
        return rechargeList.size();
    }

    public List<MobileRecharge> searchByMobileNumber(String mobileNumber) {
        return rechargeList.stream()
                .filter(recharge -> recharge.getMobileNumber() != null
                        && recharge.getMobileNumber().equals(mobileNumber))
                .toList();
    }
}