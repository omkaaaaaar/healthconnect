package com.healthconnect.controller;

import com.healthconnect.entity.Appointment;
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
public class AppointmentController {

        @Autowired
        private AppointmentRepository appointmentRepository;

        // =====================================
        // BOOK APPOINTMENT PAGE
        // =====================================

        @GetMapping("/book-appointment")
        public String bookAppointmentPage(

                        @RequestParam(required = false) String doctorName,

                        @RequestParam(required = false) String city,

                        Model model) {

                model.addAttribute(
                                "doctorName",
                                doctorName);

                model.addAttribute(
                                "city",
                                city);

                return "appointment/book-appointment";
        }

        // =====================================
        // SAVE APPOINTMENT
        // =====================================

        @PostMapping("/save-appointment")
        public String saveAppointment(

                        @RequestParam String patientName,

                        @RequestParam String doctorName,

                        @RequestParam String city) {

                Appointment appointment = new Appointment();

                appointment.setPatientName(
                                patientName);

                appointment.setDoctorName(
                                doctorName);

                appointment.setCity(
                                city);

                appointment.setStatus(
                                "Pending");

                appointmentRepository.save(
                                appointment);

                return "redirect:/my-appointments";
        }

        // =====================================
        // VIEW APPOINTMENTS
        // =====================================

        @GetMapping("/appointments")
        public String appointments(

                        HttpSession session,

                        Model model) {

                String doctorName = (String) session.getAttribute(
                                "doctorName");

                List<Appointment> appointments = appointmentRepository
                                .findByDoctorName(
                                                doctorName);

                model.addAttribute(
                                "appointments",
                                appointments);

                return "appointment/appointments";
        }

        // =====================================
        // APPOINTMENT APPROVAL PAGE
        // =====================================

        @GetMapping("/appointment-approval")
        public String appointmentApproval(

                        HttpSession session,

                        Model model) {

                String doctorName = (String) session.getAttribute(
                                "doctorName");

                List<Appointment> appointments = appointmentRepository
                                .findByDoctorName(
                                                doctorName);

                model.addAttribute(
                                "appointments",
                                appointments);

                return "appointment/appointment-approval";
        }

        // =====================================
        // ACCEPT APPOINTMENT
        // =====================================

        @PostMapping("/accept-appointment")
        public String acceptAppointment(

                        @RequestParam int id,

                        @RequestParam String appointmentDate,

                        @RequestParam String appointmentTime) {

                Appointment appointment = appointmentRepository
                                .findById(id)
                                .get();

                appointment.setStatus(
                                "Accepted");

                appointment.setAppointmentDate(
                                appointmentDate);

                appointment.setAppointmentTime(
                                appointmentTime);

                appointmentRepository.save(
                                appointment);

                return "redirect:/appointment-approval";
        }

        // =====================================
        // REJECT APPOINTMENT
        // =====================================

        @PostMapping("/reject-appointment")
        public String rejectAppointment(
                        @RequestParam int id) {

                Appointment appointment = appointmentRepository
                                .findById(id)
                                .get();

                appointment.setStatus(
                                "Rejected");

                appointmentRepository.save(
                                appointment);

                return "redirect:/appointment-approval";
        }
}