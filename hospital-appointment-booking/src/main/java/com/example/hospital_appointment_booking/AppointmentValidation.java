package com.example.hospital_appointment_booking;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class AppointmentValidation {

    public void validateAppointment(HospitalAppointment appointment) {

        if (appointment.getAppointmentDate() == null
                || appointment.getAppointmentDate().isBlank()) {
            throw new IllegalArgumentException(
                    "Appointment date is required");
        }

        LocalDate date;

        try {
            date = LocalDate.parse(appointment.getAppointmentDate());
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Appointment date must use YYYY-MM-DD format");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Appointment date cannot be in the past");
        }
    }
}