// Handles: routes, browser rquests, and rendering HTML templates (Thymeleaf), 
// ex: LoginController, PatientController, DoctorController,etc.

package com.healthconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Maps the root URL to the homePage method, FastAPI:- @app.get("/")
    public String homePage() {
        return "index"; // Render index.html from src/main/resources/templates
    }

}
