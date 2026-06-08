package com.healthconnect.controller;

import com.healthconnect.entity.Doctor;
import com.healthconnect.entity.Patient;
import com.healthconnect.entity.Pharmacist;

import com.healthconnect.repository.DoctorRepository;
import com.healthconnect.repository.PatientRepository;
import com.healthconnect.repository.PharmacistRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    // ====================================
    // SIGNUP PAGE
    // ====================================

    @GetMapping("/signup")
    public String signupPage() {

        return "auth/signup";
    }

    // ====================================
    // LOGIN PAGE
    // ====================================

    @GetMapping("/login")
    public String loginPage() {

        return "auth/login";
    }

    // ====================================
    // SIGNUP LOGIC
    // ====================================

    @PostMapping("/signup")
    public String signup(

            @RequestParam String role,

            @RequestParam String fullName,

            @RequestParam String email,

            @RequestParam String city,

            @RequestParam String password,

            @RequestParam(required = false) String specialization,

            @RequestParam(required = false) String pharmacyName,

            @RequestParam(required = false) String pharmacyAddress) {

        // =========================
        // PATIENT
        // =========================

        if (role.equals("PATIENT")) {

            Patient patient = new Patient();

            patient.setFullName(fullName);

            patient.setEmail(email);

            patient.setCity(city);

            patient.setPassword(password);

            patientRepository.save(patient);
        }

        // =========================
        // DOCTOR
        // =========================

        else if (role.equals("DOCTOR")) {

            Doctor doctor = new Doctor();

            doctor.setFullName(fullName);

            doctor.setEmail(email);

            doctor.setCity(city);

            doctor.setPassword(password);

            doctor.setSpecialization(specialization);

            doctorRepository.save(doctor);
        }

        // =========================
        // PHARMACIST
        // =========================

        else if (role.equals("PHARMACIST")) {

            Pharmacist pharmacist = new Pharmacist();

            pharmacist.setFullName(fullName);

            pharmacist.setEmail(email);

            pharmacist.setCity(city);

            pharmacist.setPassword(password);

            pharmacist.setPharmacyName(pharmacyName);

            pharmacist.setPharmacyAddress(
                    pharmacyAddress);

            pharmacistRepository.save(pharmacist);
        }

        return "redirect:/login";
    }

    // ====================================
    // LOGIN LOGIC
    // ====================================

    @PostMapping("/login")
    public String login(

            @RequestParam String role,

            @RequestParam String email,

            @RequestParam String password,

            HttpSession session) {

        // =========================
        // PATIENT LOGIN
        // =========================

        if (role.equals("PATIENT")) {

            Patient patient = patientRepository
                    .findByEmailAndPassword(
                            email,
                            password);

            if (patient != null) {

                session.setAttribute(
                        "patientName",
                        patient.getFullName());

                session.setAttribute(
                        "patientCity",
                        patient.getCity());

                return "redirect:/patient-dashboard";
            }
        }

        // =========================
        // DOCTOR LOGIN
        // =========================

        else if (role.equals("DOCTOR")) {

            Doctor doctor = doctorRepository
                    .findByEmailAndPassword(
                            email,
                            password);

            if (doctor != null) {

                session.setAttribute(
                        "doctorName",
                        doctor.getFullName());

                session.setAttribute(
                        "doctorCity",
                        doctor.getCity());

                session.setAttribute(
                        "doctorId",
                        doctor.getId());

                return "redirect:/doctor-dashboard";
            }
        }

        // =========================
        // PHARMACIST LOGIN
        // =========================

        else if (role.equals("PHARMACIST")) {

            Pharmacist pharmacist = pharmacistRepository
                    .findByEmailAndPassword(
                            email,
                            password);

            if (pharmacist != null) {

                session.setAttribute(
                        "pharmacyName",
                        pharmacist.getPharmacyName());

                session.setAttribute(
                        "pharmacyCity",
                        pharmacist.getCity());

                session.setAttribute(
                        "pharmacyAddress",
                        pharmacist.getPharmacyAddress());

                return "redirect:/pharmacist-dashboard";
            }
        }

        return "redirect:/login";
    }

    // ====================================
    // LOGOUT
    // ====================================

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}