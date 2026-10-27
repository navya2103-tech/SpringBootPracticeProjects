package com.example.employee_leave_management;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeLeaveService {

    private final List<EmployeeLeave> leaveList = new ArrayList<>();

    private final LeaveValidation leaveValidation;

    public EmployeeLeaveService(LeaveValidation leaveValidation) {
        this.leaveValidation = leaveValidation;
    }

    private int nextId = 1;

    public EmployeeLeave addLeave(EmployeeLeave leave) {

        leaveValidation.validateLeave(leave);

        leave.setEmployeeId(nextId++);
        leaveList.add(leave);

        return leave;
    }
    public List<EmployeeLeave> getAllLeaves() {
        return leaveList;
    }

    public EmployeeLeave getLeaveById(int id) {

        return leaveList.stream()
                .filter(leave -> leave.getEmployeeId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave request not found with ID: " + id
                        ));
    }

    public EmployeeLeave updateLeave(int id, EmployeeLeave updatedLeave) {

        leaveValidation.validateLeave(updatedLeave);

        EmployeeLeave existingLeave = getLeaveById(id);

        existingLeave.setEmployeeName(updatedLeave.getEmployeeName());
        existingLeave.setLeaveType(updatedLeave.getLeaveType());
        existingLeave.setNumberOfDays(updatedLeave.getNumberOfDays());
        existingLeave.setReason(updatedLeave.getReason());

        return existingLeave;
    }

    public String deleteLeave(int id) {

        EmployeeLeave leave = getLeaveById(id);
        leaveList.remove(leave);

        return "Leave request deleted successfully";
    }

    public List<EmployeeLeave> searchByEmployeeName(String name) {

        return leaveList.stream()
                .filter(leave -> leave.getEmployeeName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public long countLeaves() {
        return leaveList.size();
    }

    public List<EmployeeLeave> getLeavesByType(String type) {

        return leaveList.stream()
                .filter(leave -> leave.getLeaveType()
                        .equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}