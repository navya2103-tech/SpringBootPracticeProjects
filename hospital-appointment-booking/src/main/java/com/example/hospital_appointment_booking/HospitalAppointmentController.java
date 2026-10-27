package com.example.hospital_appointment_booking;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class HospitalAppointmentController {

    private final HospitalAppointmentService service;

    public HospitalAppointmentController(HospitalAppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public HospitalAppointment addAppointment(
            @Valid @RequestBody HospitalAppointment appointment) {
        return service.addAppointment(appointment);
    }

    @GetMapping
    public List<HospitalAppointment> getAllAppointments() {
        return service.getAllAppointments();
    }

    @GetMapping("/{id}")
    public HospitalAppointment getAppointmentById(@PathVariable int id) {
        HospitalAppointment appointment = service.getAppointmentById(id);

        if (appointment == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Appointment not found with ID: " + id);
        }

        return appointment;
    }

    @PutMapping("/{id}")
    public HospitalAppointment updateAppointment(
            @PathVariable int id,
            @Valid @RequestBody HospitalAppointment appointment) {
        HospitalAppointment updated = service.updateAppointment(id, appointment);

        if (updated == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Appointment not found with ID: " + id);
        }

        return updated;
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable int id) {
        if (!service.deleteAppointment(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Appointment not found with ID: " + id);
        }

        return "Appointment deleted successfully";
    }

    @GetMapping("/count")
    public long countAppointments() {
        return service.countAppointments();
    }

    @GetMapping("/search")
    public List<HospitalAppointment> searchByPatientName(
            @RequestParam String name) {
        return service.searchByPatientName(name);
    }

    @GetMapping("/doctor/{doctorName}")
    public List<HospitalAppointment> getAppointmentsByDoctor(
            @PathVariable String doctorName) {
        return service.getAppointmentsByDoctor(doctorName);
    }
}