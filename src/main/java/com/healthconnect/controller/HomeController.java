package com.healthconnect.controller;

import com.healthconnect.entity.Doctor;
import com.healthconnect.entity.Patient;
import com.healthconnect.entity.Pharmacist;
import com.healthconnect.entity.Medicine;
import com.healthconnect.entity.Appointment;

import com.healthconnect.repository.DoctorRepository;
import com.healthconnect.repository.PatientRepository;
import com.healthconnect.repository.PharmacistRepository;
import com.healthconnect.repository.MedicineRepository;
import com.healthconnect.repository.AppointmentRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // =================================
    // HOME
    // =================================

    @GetMapping("/")
    public String homePage() {

        return "index";
    }

    // =================================
    // PATIENT
    // =================================

    @GetMapping("/patient/register")
    public String patientRegisterPage() {

        return "patient/register";
    }

    @PostMapping("/save-patient")
    public String savePatient(Patient patient) {

        patientRepository.save(patient);

        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String patientList(Model model) {

        model.addAttribute(
                "patients",
                patientRepository.findAll());

        return "patient/list";
    }

    // =================================
    // DOCTOR
    // =================================

    @GetMapping("/doctor/register")
    public String doctorRegisterPage() {

        return "doctor/register";
    }

    @PostMapping("/save-doctor")
    public String saveDoctor(Doctor doctor) {

        doctorRepository.save(doctor);

        return "redirect:/doctors";
    }

    @GetMapping("/doctors")
    public String doctorList(Model model) {

        model.addAttribute(
                "doctors",
                doctorRepository.findAll());

        return "doctor/list";
    }

    // =================================
    // SEARCH DOCTORS
    // =================================

    @GetMapping("/patient/search-doctors")
    public String searchDoctorPage() {

        return "patient/search-doctors";
    }

    @GetMapping("/search-doctors")
    public String searchDoctors(
            @RequestParam String city,
            Model model) {

        List<Doctor> doctors = doctorRepository.findByCity(city);

        model.addAttribute(
                "doctors",
                doctors);

        return "doctor/list";
    }

    // =================================
    // PHARMACIST
    // =================================

    @GetMapping("/pharmacist/register")
    public String pharmacistRegisterPage() {

        return "pharmacist/register";
    }

    @PostMapping("/save-pharmacist")
    public String savePharmacist(
            Pharmacist pharmacist) {

        pharmacistRepository.save(pharmacist);

        return "redirect:/pharmacists";
    }

    @GetMapping("/pharmacists")
    public String pharmacistList(
            Model model) {

        model.addAttribute(
                "pharmacists",
                pharmacistRepository.findAll());

        return "pharmacist/list";
    }

    // =================================
    // MEDICINES
    // =================================

    @GetMapping("/pharmacist/add-medicine")
    public String addMedicinePage() {

        return "pharmacist/add-medicine";
    }

    @PostMapping("/save-medicine")
    public String saveMedicine(
            Medicine medicine) {

        medicineRepository.save(medicine);

        return "redirect:/medicines";
    }

    @GetMapping("/medicines")
    public String medicineList(
            Model model) {

        model.addAttribute(
                "medicines",
                medicineRepository.findAll());

        return "pharmacist/medicine-list";
    }

    // =================================
    // PATIENT DASHBOARD
    // =================================

    @GetMapping("/patient-dashboard")
    public String patientDashboard(
            HttpSession session,
            Model model) {

        model.addAttribute(
                "patientName",
                session.getAttribute("patientName"));

        model.addAttribute(
                "patientCity",
                session.getAttribute("patientCity"));

        return "patient/patient-dashboard";
    }

    // =================================
    // PATIENT APPOINTMENTS
    // =================================

    @GetMapping("/my-appointments")
    public String myAppointments(
            HttpSession session,
            Model model) {

        String patientName = (String) session.getAttribute(
                "patientName");

        List<Appointment> appointments = appointmentRepository
                .findByPatientName(
                        patientName);

        model.addAttribute(
                "appointments",
                appointments);

        return "patient/my-appointments";
    }

    // =================================
    // DOCTOR DASHBOARD
    // =================================

    @GetMapping("/doctor-dashboard")
    public String doctorDashboard(
            HttpSession session,
            Model model) {

        Integer doctorId = (Integer) session.getAttribute(
                "doctorId");

        if (doctorId == null) {

            return "redirect:/doctor-login";
        }

        Doctor doctor = doctorRepository.findById(
                doctorId).orElse(null);

        if (doctor == null) {

            return "redirect:/doctor-login";
        }

        List<Appointment> pendingAppointments = appointmentRepository
                .findByDoctorNameAndStatus(
                        doctor.getFullName(),
                        "Pending");

        model.addAttribute(
                "doctor",
                doctor);

        model.addAttribute(
                "doctorName",
                doctor.getFullName());

        model.addAttribute(
                "doctorCity",
                doctor.getCity());

        model.addAttribute(
                "pendingCount",
                pendingAppointments.size());

        return "doctor/doctor-dashboard";
    }

    // =================================
    // UPDATE DOCTOR PROFILE PAGE
    // =================================

    @GetMapping("/doctor/update-profile")
    public String updateDoctorProfilePage(
            HttpSession session,
            Model model) {

        String doctorName = (String) session.getAttribute("doctorName");

        if (doctorName == null) {

            return "redirect:/doctor-login";
        }

        Doctor doctor = doctorRepository.findByFullName(doctorName);

        if (doctor == null) {

            return "redirect:/doctor-login";
        }

        model.addAttribute(
                "doctor",
                doctor);

        return "doctor/update-profile";
    }

    // =================================
    // SAVE DOCTOR PROFILE
    // =================================

    @PostMapping("/doctor/update-profile")
    public String updateDoctorProfile(
            Doctor updatedDoctor,
            HttpSession session) {

        String doctorName = (String) session.getAttribute(
                "doctorName");

        Doctor doctor = doctorRepository.findByFullName(
                doctorName);

        doctor.setSpecialization(
                updatedDoctor.getSpecialization());

        doctor.setPhoneNumber(
                updatedDoctor.getPhoneNumber());

        doctor.setClinicAddress(
                updatedDoctor.getClinicAddress());

        doctor.setTimings(
                updatedDoctor.getTimings());

        doctorRepository.save(
                doctor);

        return "redirect:/doctor-dashboard";
    }

    // =================================
    // PHARMACIST DASHBOARD
    // =================================

    @GetMapping("/pharmacist-dashboard")
    public String pharmacistDashboard(
            HttpSession session,
            Model model) {

        model.addAttribute(
                "pharmacyName",
                session.getAttribute("pharmacyName"));

        model.addAttribute(
                "pharmacyCity",
                session.getAttribute("pharmacyCity"));

        return "pharmacist/pharmacist-dashboard";
    }

}
