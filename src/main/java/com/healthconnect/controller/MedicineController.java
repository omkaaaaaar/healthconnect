package com.healthconnect.controller;

import com.healthconnect.entity.Medicine;
import com.healthconnect.repository.MedicineRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/search-medicines")
    public String searchMedicines(

            @RequestParam(required = false) String medicineName,

            HttpSession session,

            Model model) {

        String city = (String) session.getAttribute(
                "patientCity");

        List<Medicine> medicines;

        // BEFORE SEARCH

        if (medicineName == null ||
                medicineName.isEmpty()) {

            medicines = medicineRepository
                    .findByCity(city);

        }

        // AFTER SEARCH

        else {

            medicines = medicineRepository
                    .findByMedicineNameContainingIgnoreCaseAndCity(
                            medicineName,
                            city);
        }

        model.addAttribute(
                "medicines",
                medicines);

        return "medicine/search-medicines";
    }
}