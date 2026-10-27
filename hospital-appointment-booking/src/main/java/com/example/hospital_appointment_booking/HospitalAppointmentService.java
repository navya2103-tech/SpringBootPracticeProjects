package com.example.hospital_appointment_booking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class HospitalAppointmentService {

    private final List<HospitalAppointment> appointments = new ArrayList<>();

    private final AppointmentValidation validation;

    public HospitalAppointmentService(AppointmentValidation validation) {
        this.validation = validation;
    }

    public HospitalAppointment addAppointment(HospitalAppointment appointment) {
        validation.validateAppointment(appointment);

        if (getAppointmentById(appointment.getAppointmentId()) != null) {
            throw new IllegalArgumentException("Appointment ID already exists");
        }

        appointments.add(appointment);
        return appointment;
    }

    public List<HospitalAppointment> getAllAppointments() {
        return appointments;
    }

    public HospitalAppointment getAppointmentById(int id) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId() == id)
                .findFirst()
                .orElse(null);
    }

    public HospitalAppointment updateAppointment(
            int id, HospitalAppointment updatedAppointment) {

        HospitalAppointment existing = getAppointmentById(id);

        if (existing == null) {
            return null;
        }

        validation.validateAppointment(updatedAppointment);

        existing.setPatientName(updatedAppointment.getPatientName());
        existing.setDoctorName(updatedAppointment.getDoctorName());
        existing.setDisease(updatedAppointment.getDisease());
        existing.setAppointmentDate(updatedAppointment.getAppointmentDate());

        return existing;
    }

    public boolean deleteAppointment(int id) {
        return appointments.removeIf(a -> a.getAppointmentId() == id);
    }

    public long countAppointments() {
        return appointments.stream().count();
    }

    public List<HospitalAppointment> searchByPatientName(String name) {
        return appointments.stream()
                .filter(a -> a.getPatientName() != null
                        && a.getPatientName().toLowerCase()
                                .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<HospitalAppointment> getAppointmentsByDoctor(String doctorName) {
        return appointments.stream()
                .filter(a -> a.getDoctorName() != null
                        && a.getDoctorName().equalsIgnoreCase(doctorName))
                .collect(Collectors.toList());
    }
}