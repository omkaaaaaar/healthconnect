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
public class LoginController {

        @Autowired
        private PatientRepository patientRepository;

        @Autowired
        private DoctorRepository doctorRepository;

        @Autowired
        private PharmacistRepository pharmacistRepository;

        // =========================
        // PATIENT LOGIN
        // =========================

        @GetMapping("/patient-login")
        public String patientLoginPage() {
                return "patient/patient-login";
        }

        @PostMapping("/patient-login")
        public String patientLogin(
                        @RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

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

                return "redirect:/patient-login";
        }

        // =========================
        // DOCTOR LOGIN
        // =========================

        @PostMapping("/doctor-login")
        public String doctorLogin(
                        @RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

                Doctor doctor = doctorRepository.findByEmailAndPassword(
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

                return "redirect:/doctor-login";
        }

        // =========================
        // PHARMACIST LOGIN
        // =========================

        @GetMapping("/pharmacist-login")
        public String pharmacistLoginPage() {
                return "pharmacist/pharmacist-login";
        }

        @PostMapping("/pharmacist-login")
        public String pharmacistLogin(
                        @RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

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

                return "redirect:/pharmacist-login";
        }

}