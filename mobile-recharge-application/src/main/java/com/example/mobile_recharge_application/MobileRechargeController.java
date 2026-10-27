package com.example.mobile_recharge_application;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recharges")
public class MobileRechargeController {

    private final MobileRechargeService service;

    public MobileRechargeController(MobileRechargeService service) {
        this.service = service;
    }

    @GetMapping
    public List<MobileRecharge> getAllRecharges() {
        return service.getAllRecharges();
    }

    @GetMapping("/{id}")
    public MobileRecharge getRechargeById(@PathVariable int id) {
        MobileRecharge recharge = service.getRechargeById(id);

        if (recharge == null) {
            throw new RechargeNotFoundException(
                    "Recharge not found with ID: " + id);
        }

        return recharge;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MobileRecharge addRecharge(
            @Valid @RequestBody MobileRecharge recharge) {
        return service.addRecharge(recharge);
    }

    @DeleteMapping("/{id}")
    public String deleteRecharge(@PathVariable int id) {
        boolean deleted = service.deleteRecharge(id);

        if (!deleted) {
            throw new RechargeNotFoundException(
                    "Recharge not found with ID: " + id);
        }

        return "Recharge deleted successfully";
    }

    @GetMapping("/count")
    public long countRecharges() {
        return service.countRecharges();
    }

    @GetMapping("/search")
    public List<MobileRecharge> searchByMobileNumber(
            @RequestParam String mobileNumber) {
        return service.searchByMobileNumber(mobileNumber);
    }
}