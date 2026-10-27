package com.example.employee_leave_management;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService service;

    public EmployeeLeaveController(EmployeeLeaveService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeLeave> getAllLeaves() {
        return service.getAllLeaves();
    }

    @GetMapping("/{id}")
    public EmployeeLeave getLeaveById(@PathVariable int id) {
        return service.getLeaveById(id);
    }

    @PostMapping
    public EmployeeLeave addLeave(
            @Valid @RequestBody EmployeeLeave leave) {
        return service.addLeave(leave);
    }

    @PutMapping("/{id}")
    public EmployeeLeave updateLeave(
            @PathVariable int id,
            @Valid @RequestBody EmployeeLeave leave) {
        return service.updateLeave(id, leave);
    }

    @DeleteMapping("/{id}")
    public String deleteLeave(@PathVariable int id) {
        return service.deleteLeave(id);
    }

    @GetMapping("/search")
    public List<EmployeeLeave> searchByEmployeeName(
            @RequestParam String name) {
        return service.searchByEmployeeName(name);
    }

    @GetMapping("/count")
    public long countLeaves() {
        return service.countLeaves();
    }

    @GetMapping("/type/{type}")
    public List<EmployeeLeave> getLeavesByType(
            @PathVariable String type) {
        return service.getLeavesByType(type);
    }
}