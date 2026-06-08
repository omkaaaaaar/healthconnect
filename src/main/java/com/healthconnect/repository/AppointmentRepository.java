package com.healthconnect.repository;

import com.healthconnect.entity.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Integer> {

    // DOCTOR APPOINTMENTS
    List<Appointment> findByDoctorName(
            String doctorName);

    // PENDING APPOINTMENTS
    List<Appointment> findByDoctorNameAndStatus(
            String doctorName,
            String status);

    // PATIENT APPOINTMENTS
    List<Appointment> findByPatientName(
            String patientName);

}
