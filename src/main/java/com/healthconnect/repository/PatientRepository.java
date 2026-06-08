package com.healthconnect.repository;

import com.healthconnect.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findByEmailAndPassword(
            String email,
            String password);

}
