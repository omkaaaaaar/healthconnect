package com.healthconnect.repository;

import com.healthconnect.entity.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository
        extends JpaRepository<Doctor, Integer> {

    List<Doctor> findByCity(String city);

    Doctor findByFullName(String fullName);

    Doctor findByEmailAndPassword(
            String email,
            String password);

}
