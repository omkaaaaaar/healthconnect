package com.healthconnect.controller;

import com.healthconnect.entity.Doctor;
import com.healthconnect.entity.Patient;
import com.healthconnect.repository.DoctorRepository;
import com.healthconnect.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.healthconnect.entity.Appointment;
import com.healthconnect.repository.AppointmentRepository;
import java.util.Optional;
import com.healthconnect.entity.Pharmacist;
import com.healthconnect.repository.PharmacistRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

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

        List<Patient> patients = patientRepository.findAll();

        model.addAttribute("patients", patients);

        return "patient/list";
    }

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

        List<Doctor> doctors = doctorRepository.findAll();

        model.addAttribute("doctors", doctors);

        return "doctor/list";
    }

    @GetMapping("/patient/search-doctors")
    public String searchDoctorPage() {
        return "patient/search-doctors";
    }

    @GetMapping("/search-doctors")
    public String searchDoctors(@RequestParam String city, Model model) {

        List<Doctor> doctors = doctorRepository.findByCity(city);

        model.addAttribute("doctors", doctors);

        return "doctor/list";

    }

    @GetMapping("/patient/book-appointment")
    public String bookAppointmentPage() {
        return "patient/book-appointment";
    }

    @PostMapping("/save-appointment")
    public String saveAppointment(Appointment appointment) {

        appointment.setStatus("Pending");

        appointmentRepository.save(appointment);

        return "redirect:/appointments";

    }

    @GetMapping("/appointments")
    public String appointmentList(Model model) {

        List<Appointment> appointments = appointmentRepository.findAll();

        model.addAttribute("appointments", appointments);

        return "doctor/appointments";

    }

    @GetMapping("/appointment/accept/{id}")
    public String acceptAppointment(@org.springframework.web.bind.annotation.PathVariable int id) {

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {

            Appointment appointment = optionalAppointment.get();

            appointment.setStatus("Accepted");

            appointmentRepository.save(appointment);
        }

        return "redirect:/appointments";

    }

    @GetMapping("/appointment/reject/{id}")
    public String rejectAppointment(@org.springframework.web.bind.annotation.PathVariable int id) {

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {

            Appointment appointment = optionalAppointment.get();

            appointment.setStatus("Rejected");

            appointmentRepository.save(appointment);
        }

        return "redirect:/appointments";

    }

    @GetMapping("/pharmacist/register")
    public String pharmacistRegisterPage() {
        return "pharmacist/register";
    }

    @PostMapping("/save-pharmacist")
    public String savePharmacist(Pharmacist pharmacist) {

        pharmacistRepository.save(pharmacist);

        return "redirect:/pharmacists";

    }

    @GetMapping("/pharmacists")
    public String pharmacistList(Model model) {

        model.addAttribute("pharmacists",
                pharmacistRepository.findAll());

        return "pharmacist/list";

    }

}
