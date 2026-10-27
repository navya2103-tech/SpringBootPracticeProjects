package com.example.employee_leave_management;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LeaveValidation {

    private final List<String> allowedLeaveTypes =
            Arrays.asList("CASUAL", "SICK", "EARNED");

    public boolean isValidLeaveType(String leaveType) {
        if (leaveType == null || leaveType.isBlank()) {
            return false;
        }

        return allowedLeaveTypes.contains(
                leaveType.trim().toUpperCase()
        );
    }

    public void validateLeave(EmployeeLeave leave) {

        if (!isValidLeaveType(leave.getLeaveType())) {
            throw new IllegalArgumentException(
                    "Leave type must be CASUAL, SICK, or EARNED"
            );
        }

        if (leave.getNumberOfDays() < 1) {
            throw new IllegalArgumentException(
                    "Leave days must be at least 1"
            );
        }
    }
}