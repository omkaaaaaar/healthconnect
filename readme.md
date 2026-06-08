# 🏥 HealthConnect

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-green)
![Status](https://img.shields.io/badge/Status-MVP%20Functional-success)

### Full Stack Healthcare Management Platform

A modern healthcare ecosystem connecting **Patients**, **Doctors**, and **Pharmacists** through a centralized platform.

</div>

---

# 📌 Project Overview

HealthConnect is a full-stack healthcare platform inspired by:

- Practo
- Apollo 24/7
- Tata 1mg

The platform enables:

✅ Patient Registration & Login  
✅ Doctor Registration & Login  
✅ Pharmacist Registration & Login  
✅ Doctor Search by City  
✅ Medicine Search by Name  
✅ Appointment Booking System  
✅ Appointment Approval Workflow  
✅ Doctor Dashboard  
✅ Patient Dashboard  
✅ Pharmacist Dashboard  
✅ Session-Based Authentication  
✅ Profile Management

The project is currently built using:

- Java Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- HTML/CSS/JavaScript

---

# 🧠 System Architecture

## MVC Architecture

```text
Client (Browser)
        ↓
Controller Layer
        ↓
Repository Layer
        ↓
MySQL Database
        ↓
Thymeleaf Templates
```

---

# ⚙️ Tech Stack

| Technology      | Purpose                |
| --------------- | ---------------------- |
| Java 17         | Backend Development    |
| Spring Boot     | Application Framework  |
| Spring MVC      | Routing & Controllers  |
| Spring Data JPA | Database Operations    |
| Thymeleaf       | Dynamic HTML Rendering |
| MySQL           | Database               |
| HTML/CSS/JS     | Frontend               |
| Maven           | Dependency Management  |

---

# 📁 Project Structure

```text
src/main/java/com/healthconnect
│
├── controller
│   ├── HomeController.java
│   ├── LoginController.java
│   └── AppointmentController.java
│
├── entity
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Pharmacist.java
│   ├── Medicine.java
│   └── Appointment.java
│
├── repository
│   ├── PatientRepository.java
│   ├── DoctorRepository.java
│   ├── PharmacistRepository.java
│   ├── MedicineRepository.java
│   └── AppointmentRepository.java
│
└── HealthconnectApplication.java
```

---

# 🗄️ Database Entities

---

# 👤 Patient Entity

Stores patient information.

## Fields

```java
id
fullName
email
city
password
```

## Used For

- Patient registration
- Patient login
- Doctor search
- Appointment booking
- Medicine search

---

# 👨‍⚕️ Doctor Entity

Stores doctor profile information.

## Fields

```java
id
fullName
email
city
specialization
password
phoneNumber
clinicAddress
timings
```

## Used For

- Doctor login
- Doctor dashboard
- Appointment approval
- Profile update

---

# 💊 Pharmacist Entity

Stores pharmacy details.

## Fields

```java
id
pharmacyName
email
city
pharmacyAddress
password
```

## Used For

- Pharmacist login
- Medicine inventory
- Medicine search system

---

# 💉 Medicine Entity

Stores medicine inventory data.

## Fields

```java
id
medicineName
quantity
pharmacyName
city
address
```

## Features

- Partial medicine search
- Same-city filtering
- Pharmacy discovery

---

# 📅 Appointment Entity

Stores appointment records.

## Fields

```java
id
patientName
doctorName
city
status
appointmentDate
appointmentTime
```

## Appointment Status

```text
Pending
Accepted
Rejected
```

---

# 🔐 Authentication System

Authentication is implemented using:

```java
HttpSession
```

---

# 👤 Patient Login Flow

## Route

```text
/patient-login
```

## Session Data Stored

```java
session.setAttribute("patientName", patient.getFullName());
session.setAttribute("patientCity", patient.getCity());
```

---

# 👨‍⚕️ Doctor Login Flow

## Route

```text
/doctor-login
```

## Session Data Stored

```java
session.setAttribute("doctorName", doctor.getFullName());
session.setAttribute("doctorCity", doctor.getCity());
```

---

# 💊 Pharmacist Login Flow

## Route

```text
/pharmacist-login
```

## Session Data Stored

```java
session.setAttribute("pharmacyName", pharmacist.getPharmacyName());
session.setAttribute("pharmacyCity", pharmacist.getCity());
```

---

# 🌐 Routing System

The application uses:

```java
@GetMapping
@PostMapping
```

for page routing and backend request handling.

---

# 🧭 Main Application Routes

---

# 🏠 Home Routes

| Route | Description |
| ----- | ----------- |
| `/`   | Home Page   |

---

# 👤 Patient Routes

| Route                     | Description                 |
| ------------------------- | --------------------------- |
| `/patient/register`       | Patient Registration Page   |
| `/save-patient`           | Save Patient                |
| `/patient-login`          | Patient Login               |
| `/patient-dashboard`      | Patient Dashboard           |
| `/patient/search-doctors` | Search Doctors Page         |
| `/search-doctors`         | Search Doctors by City      |
| `/my-appointments`        | Patient Appointment History |

---

# 👨‍⚕️ Doctor Routes

| Route                    | Description               |
| ------------------------ | ------------------------- |
| `/doctor/register`       | Doctor Registration       |
| `/save-doctor`           | Save Doctor               |
| `/doctor-login`          | Doctor Login              |
| `/doctor-dashboard`      | Doctor Dashboard          |
| `/appointment-approval`  | Appointment Approval Page |
| `/doctor/update-profile` | Update Doctor Profile     |

---

# 💊 Pharmacist Routes

| Route                      | Description             |
| -------------------------- | ----------------------- |
| `/pharmacist/register`     | Pharmacist Registration |
| `/save-pharmacist`         | Save Pharmacist         |
| `/pharmacist-login`        | Pharmacist Login        |
| `/pharmacist-dashboard`    | Pharmacist Dashboard    |
| `/pharmacist/add-medicine` | Add Medicine Page       |
| `/save-medicine`           | Save Medicine           |
| `/medicines`               | Medicine List           |

---

# 📅 Appointment Routes

| Route                 | Description           |
| --------------------- | --------------------- |
| `/book-appointment`   | Book Appointment Page |
| `/save-appointment`   | Save Appointment      |
| `/appointments`       | Appointment List      |
| `/accept-appointment` | Accept Appointment    |
| `/reject-appointment` | Reject Appointment    |

---

# 🔎 Medicine Search System

The medicine search system supports:

## ✅ Partial Search

Example:

```text
Dolo
```

returns:

```text
Dolo 650
Dolo Cold
Dolo Plus
```

using:

```java
findByMedicineNameContainingIgnoreCase()
```

---

# 🌍 Same City Filtering

Only medicines from pharmacies in the patient's city are shown.

Example:

```text
Patient City = Mumbai
```

Only Mumbai pharmacies appear.

---

# 📅 Appointment Workflow

---

# 👤 Patient Side

Patients can:

- Search doctors
- Book appointments
- View appointment status

---

# 👨‍⚕️ Doctor Side

Doctors can:

- View pending appointments
- Accept appointments
- Reject appointments
- Select appointment date
- Select appointment timing

---

# 🔁 Workflow Example

```text
Patient Books Appointment
            ↓
Appointment Status = Pending
            ↓
Doctor Opens Approval Dashboard
            ↓
Doctor Accepts or Rejects
            ↓
Selected Date & Time Saved
            ↓
Patient Dashboard Updated
```

---

# 📊 Dashboard System

---

# 👤 Patient Dashboard

Displays:

- Patient information
- Doctor search
- Medicine search
- Appointment status

---

# 👨‍⚕️ Doctor Dashboard

Displays:

- Doctor information
- Specialization
- Clinic address
- Timings
- Phone number
- Pending appointment count

Actions:

- Appointment approval
- Profile update
- Logout

---

# 💊 Pharmacist Dashboard

Displays:

- Pharmacy details
- Medicine inventory management

---

# 🧩 Repository Layer

Uses:

```java
JpaRepository
```

for database access.

---

# 📌 Example Query Methods

## Doctor Search

```java
findByCity(String city)
```

---

## Pending Appointments

```java
findByDoctorNameAndStatus(String doctorName, String status)
```

---

## Patient Appointments

```java
findByPatientName(String patientName)
```

---

## Partial Medicine Search

```java
findByMedicineNameContainingIgnoreCase(String medicineName)
```

---

# 🎨 Frontend System

Currently built using:

- HTML
- CSS
- Thymeleaf

---

# 🖥️ Current UI Features

✅ Responsive Layout  
✅ Dashboard Cards  
✅ Form Handling  
✅ Appointment Tables  
✅ Styled Buttons  
✅ Session-Based Navigation

---

# 🚧 Current Known Issues

- Some pages still use temporary UI
- Session protection improvements pending
- Logout invalidation pending
- Global exception handling pending
- React frontend pending

---

# 🚀 Upcoming Phases

---

# ✅ Phase 24

Professional Search UI

Features:

- Doctor cards
- Medicine cards
- Empty states
- Better UX

---

# ✅ Phase 25

Security Upgrade

Features:

- Protected routes
- Session validation
- Role-based access
- Proper logout

---

# ✅ Phase 26

React Frontend Migration

Features:

- React Components
- API Integration
- Reusable UI
- Dynamic State Management

---

# ✅ Phase 27

REST API Development

Features:

- JSON APIs
- React Integration
- API Architecture

---

# ✅ Phase 28

Advanced Frontend

Features:

- Fancy CSS
- Animations
- Modern UI
- Responsive Design

---

# ✅ Phase 29

Deployment

Deployment Targets:

- Render
- Railway
- Vercel

---

# 🔥 Current MVP Status

| Module               | Status       |
| -------------------- | ------------ |
| Authentication       | ✅ Completed |
| Doctor Search        | ✅ Completed |
| Medicine Search      | ✅ Completed |
| Appointment System   | ✅ Completed |
| Doctor Dashboard     | ✅ Completed |
| Pharmacist Dashboard | ✅ Completed |
| Patient Dashboard    | ✅ Completed |
| Session Management   | ✅ Completed |
| Profile Update       | ✅ Completed |

---

# 🛠️ How To Run The Project

---

# 1️⃣ Clone Repository

```bash
git clone <repository-url>
```

---

# 2️⃣ Open Project

Open in:

```text
VS Code / IntelliJ
```

---

# 3️⃣ Configure MySQL

Create database:

```sql
CREATE DATABASE healthconnect;
```

---

# 4️⃣ Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/healthconnect
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# 5️⃣ Run Project

```bash
mvn spring-boot:run
```

---

# 6️⃣ Open Browser

```text
http://localhost:8080
```

---

# 👨‍💻 Developed By

## Omkar Patkar

Data Science & AI Engineering Student  
Konkan Gyanpeeth College of Engineering

---

# 📄 License

This project is currently under personal development and educational usage.

---

# ⭐ Future Vision

HealthConnect aims to evolve into:

- Full SaaS Healthcare Platform
- AI-powered Healthcare Assistant
- Real-time Appointment System
- Online Medicine Delivery Platform
- Telemedicine Ecosystem

---

<div align="center">

# ❤️ HealthConnect MVP Successfully Functional

</div>
