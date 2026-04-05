# 🏥 MediCore Hospital Management System
## IT4020 Modern Topics in IT — Assignment 2 | SLIIT 2026

---

## 🎨 Design System: 60-30-10 Rule

| Role | Color | Usage |
|------|-------|-------|
| **60% Dominant** | `#E6F7F9` Clean Sky | Background, cards, content areas, table rows |
| **30% Secondary** | `#0077B6` Trust Blue | Sidebar, header, nav bar, primary UI chrome |
| **10% Accent 1** | `#FF9800` Action Orange | Primary action buttons, urgent alerts, CTA |
| **10% Accent 2** | `#4CAF50` Health Green | Confirmed status, success, available indicators |

---

## 🏗️ Architecture

```
┌─────────────────┐     ┌──────────────────────────────┐
│  React Frontend  │────▶│     API Gateway :8080         │
│  :3000           │     │  (Spring Cloud Gateway)       │
│  60-30-10 theme  │     │  Single entry point           │
└─────────────────┘     └──────┬───────────────────────┘
                                │ Route Rewriting
        ┌───────────┬───────────┼───────────┬───────────┬───────────┐
        ▼           ▼           ▼           ▼           ▼           ▼
   Patient      Doctor    Appointment  Pharmacy    Billing    LabTest
   :8081        :8082       :8083       :8084       :8085      :8086
   (Member 1)  (Member 2)  (Member 3)  (Member 4)  (Member 5) (Member 6)
```

---

## 🐳 Docker — One Command Startup

### Prerequisites
- Docker Desktop installed and running
- At least 4GB RAM allocated to Docker

### Run the entire stack
```bash
# From the project root (where docker-compose.yml is)
docker-compose up --build

# Or run in background
docker-compose up --build -d
```

### Stop everything
```bash
docker-compose down
```

### View logs
```bash
docker-compose logs -f api-gateway
docker-compose logs -f patient-service
docker-compose logs -f frontend
```

### Access Points (after docker-compose up)
| Service | URL |
|---------|-----|
| 🖥️ **React Frontend** | http://localhost:3000 |
| 🔀 **API Gateway** | http://localhost:8080 |
| 📖 **Swagger UI (all services)** | http://localhost:8080/swagger-ui.html |
| 🧑‍⚕️ Patient Service direct | http://localhost:8081/swagger-ui.html |
| 👨‍⚕️ Doctor Service direct | http://localhost:8082/swagger-ui.html |
| 📅 Appointment Service direct | http://localhost:8083/swagger-ui.html |
| 💊 Pharmacy Service direct | http://localhost:8084/swagger-ui.html |
| 🧾 Billing Service direct | http://localhost:8085/swagger-ui.html |
| 🔬 Lab Test Service direct | http://localhost:8086/swagger-ui.html |

---

## 🚀 Run Without Docker (Development)

### Backend — 7 terminals
```bash
cd hospital-microservices/patient-service     && mvn spring-boot:run  # :8081
cd hospital-microservices/doctor-service      && mvn spring-boot:run  # :8082
cd hospital-microservices/appointment-service && mvn spring-boot:run  # :8083
cd hospital-microservices/pharmacy-service    && mvn spring-boot:run  # :8084
cd hospital-microservices/billing-service     && mvn spring-boot:run  # :8085
cd hospital-microservices/labtest-service     && mvn spring-boot:run  # :8086
cd hospital-microservices/api-gateway         && mvn spring-boot:run  # :8080 ← start LAST
```

### Frontend
```bash
cd hospital-frontend
npm install
npm start   # Opens http://localhost:3000
```

---

## 📁 Project Structure

```
project-root/
│
├── docker-compose.yml          ← ONE command to run everything
│
├── hospital-microservices/
│   ├── api-gateway/            ← All Members · Port 8080
│   │   ├── Dockerfile
│   │   └── src/main/resources/
│   │       ├── application.yml           (local dev)
│   │       └── application-docker.yml    (Docker — uses container hostnames)
│   │
│   ├── patient-service/        ← Member 1 · Port 8081
│   ├── doctor-service/         ← Member 2 · Port 8082
│   ├── appointment-service/    ← Member 3 · Port 8083
│   ├── pharmacy-service/       ← Member 4 · Port 8084
│   ├── billing-service/        ← Member 5 · Port 8085
│   └── labtest-service/        ← Member 6 · Port 8086
│       (each contains Dockerfile + pom.xml + src/)
│
└── hospital-frontend/          ← React App · Port 3000
    ├── Dockerfile
    ├── nginx.conf
    ├── package.json
    └── src/
        ├── App.js              (Shell + Router)
        ├── App.css             (60-30-10 design tokens)
        ├── services/api.js     (All gateway API calls)
        ├── hooks/useCRUD.js    (Reusable data fetching)
        └── pages/
            ├── Dashboard.js    (Overview + appointments table + doctor availability)
            ├── Patients.js     (Member 1 — CRUD via gateway)
            ├── Doctors.js      (Member 2 — CRUD via gateway)
            ├── Appointments.js (Member 3 — CRUD + status via gateway)
            ├── Pharmacy.js     (Member 4 — CRUD + stock via gateway)
            ├── Billing.js      (Member 5 — invoices + payments via gateway)
            └── LabTests.js     (Member 6 — tests + results via gateway)
```

---

## 🔀 Gateway Route Map

| Browser/App calls | Gateway rewrites to |
|-------------------|---------------------|
| `:8080/gateway/patients/**` | `:8081/api/patients/**` |
| `:8080/gateway/doctors/**` | `:8082/api/doctors/**` |
| `:8080/gateway/appointments/**` | `:8083/api/appointments/**` |
| `:8080/gateway/medicines/**` | `:8084/api/medicines/**` |
| `:8080/gateway/invoices/**` | `:8085/api/invoices/**` |
| `:8080/gateway/labtests/**` | `:8086/api/labtests/**` |

**In Docker:** hostnames are container names (e.g. `patient-service:8081`)
**In dev:** hostnames are `localhost:8081`

---

## 📡 API Documentation (OpenAPI 3.0)

```yaml
openapi: 3.0.3
info:
  title: MediCore Hospital Management API
  version: 1.0.0
  description: |
    REST API for MediCore Hospital Management System.
    All endpoints are accessed through the API Gateway at http://localhost:8080/gateway
  contact:
    name: MediCore Development Team
    url: http://localhost:8080

servers:
  - url: http://localhost:8080/gateway
    description: API Gateway (Docker)
  - url: http://localhost:8080/gateway
    description: API Gateway (Development)

tags:
  - name: Patient Service
    description: Patient registration and management
  - name: Doctor Service
    description: Doctor profiles and availability
  - name: Appointment Service
    description: Appointment scheduling and management
  - name: Pharmacy Service
    description: Medicine inventory and dispensing
  - name: Billing Service
    description: Invoice generation and payment processing
  - name: Lab Test Service
    description: Laboratory test ordering and results

paths:
  # ============================================================
  # PATIENT SERVICE
  # ============================================================
  /patients:
    get:
      tags: [Patient Service]
      summary: Get all patients
      operationId: getAllPatients
      responses:
        '200':
          description: List of all patients
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Patient'
    post:
      tags: [Patient Service]
      summary: Register a new patient
      operationId: createPatient
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/PatientInput'
      responses:
        '201':
          description: Patient created successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Patient'
        '400':
          description: Invalid input

  /patients/{id}:
    get:
      tags: [Patient Service]
      summary: Get patient by ID
      operationId: getPatientById
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Patient details
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Patient'
        '404':
          description: Patient not found
    put:
      tags: [Patient Service]
      summary: Update patient
      operationId: updatePatient
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/PatientInput'
      responses:
        '200':
          description: Patient updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Patient'
    delete:
      tags: [Patient Service]
      summary: Delete patient
      operationId: deletePatient
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '204':
          description: Patient deleted
        '404':
          description: Patient not found

  /patients/search:
    get:
      tags: [Patient Service]
      summary: Search patients by last name
      operationId: searchPatientsByLastName
      parameters:
        - name: lastName
          in: query
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of matching patients
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Patient'

  # ============================================================
  # DOCTOR SERVICE
  # ============================================================
  /doctors:
    get:
      tags: [Doctor Service]
      summary: Get all doctors
      operationId: getAllDoctors
      responses:
        '200':
          description: List of all doctors
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Doctor'
    post:
      tags: [Doctor Service]
      summary: Add a new doctor
      operationId: createDoctor
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/DoctorInput'
      responses:
        '201':
          description: Doctor added successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Doctor'

  /doctors/{id}:
    get:
      tags: [Doctor Service]
      summary: Get doctor by ID
      operationId: getDoctorById
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Doctor details
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Doctor'
        '404':
          description: Doctor not found
    put:
      tags: [Doctor Service]
      summary: Update doctor
      operationId: updateDoctor
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/DoctorInput'
      responses:
        '200':
          description: Doctor updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Doctor'
    delete:
      tags: [Doctor Service]
      summary: Remove doctor
      operationId: deleteDoctor
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '204':
          description: Doctor removed
        '404':
          description: Doctor not found

  /doctors/specialization/{specialization}:
    get:
      tags: [Doctor Service]
      summary: Find doctors by specialization
      operationId: getDoctorsBySpecialization
      parameters:
        - name: specialization
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of doctors with matching specialization
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Doctor'

  /doctors/available:
    get:
      tags: [Doctor Service]
      summary: Get available doctors
      operationId: getAvailableDoctors
      responses:
        '200':
          description: List of available doctors
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Doctor'

  # ============================================================
  # APPOINTMENT SERVICE
  # ============================================================
  /appointments:
    get:
      tags: [Appointment Service]
      summary: Get all appointments
      operationId: getAllAppointments
      responses:
        '200':
          description: List of all appointments
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Appointment'
    post:
      tags: [Appointment Service]
      summary: Book a new appointment
      operationId: createAppointment
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/AppointmentInput'
      responses:
        '201':
          description: Appointment booked successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Appointment'

  /appointments/{id}:
    get:
      tags: [Appointment Service]
      summary: Get appointment by ID
      operationId: getAppointmentById
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Appointment details
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Appointment'
        '404':
          description: Appointment not found
    put:
      tags: [Appointment Service]
      summary: Update appointment
      operationId: updateAppointment
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/AppointmentInput'
      responses:
        '200':
          description: Appointment updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Appointment'
    delete:
      tags: [Appointment Service]
      summary: Cancel appointment
      operationId: deleteAppointment
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '204':
          description: Appointment cancelled
        '404':
          description: Appointment not found

  /appointments/{id}/status:
    patch:
      tags: [Appointment Service]
      summary: Update appointment status
      operationId: updateAppointmentStatus
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
        - name: status
          in: query
          required: true
          schema:
            type: string
            enum: [SCHEDULED, CONFIRMED, CANCELLED, COMPLETED, NO_SHOW]
      responses:
        '200':
          description: Status updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Appointment'

  /appointments/patient/{patientId}:
    get:
      tags: [Appointment Service]
      summary: Get appointments by patient
      operationId: getAppointmentsByPatient
      parameters:
        - name: patientId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of patient's appointments
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Appointment'

  /appointments/doctor/{doctorId}:
    get:
      tags: [Appointment Service]
      summary: Get appointments by doctor
      operationId: getAppointmentsByDoctor
      parameters:
        - name: doctorId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of doctor's appointments
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Appointment'

  /appointments/date:
    get:
      tags: [Appointment Service]
      summary: Get appointments by date
      operationId: getAppointmentsByDate
      parameters:
        - name: date
          in: query
          required: true
          schema:
            type: string
            format: date
      responses:
        '200':
          description: List of appointments on specified date
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Appointment'

  # ============================================================
  # PHARMACY SERVICE (Medicines)
  # ============================================================
  /medicines:
    get:
      tags: [Pharmacy Service]
      summary: Get all medicines
      operationId: getAllMedicines
      responses:
        '200':
          description: List of all medicines
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Medicine'
    post:
      tags: [Pharmacy Service]
      summary: Add a new medicine
      operationId: createMedicine
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/MedicineInput'
      responses:
        '201':
          description: Medicine added successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Medicine'

  /medicines/{id}:
    get:
      tags: [Pharmacy Service]
      summary: Get medicine by ID
      operationId: getMedicineById
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Medicine details
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Medicine'
        '404':
          description: Medicine not found
    put:
      tags: [Pharmacy Service]
      summary: Update medicine
      operationId: updateMedicine
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/MedicineInput'
      responses:
        '200':
          description: Medicine updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Medicine'
    delete:
      tags: [Pharmacy Service]
      summary: Remove medicine
      operationId: deleteMedicine
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '204':
          description: Medicine removed
        '404':
          description: Medicine not found

  /medicines/{id}/stock:
    patch:
      tags: [Pharmacy Service]
      summary: Update medicine stock
      operationId: updateMedicineStock
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
        - name: quantity
          in: query
          required: true
          schema:
            type: integer
      responses:
        '200':
          description: Stock updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Medicine'

  /medicines/category/{category}:
    get:
      tags: [Pharmacy Service]
      summary: Get medicines by category
      operationId: getMedicinesByCategory
      parameters:
        - name: category
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of medicines in category
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Medicine'

  /medicines/search:
    get:
      tags: [Pharmacy Service]
      summary: Search medicines by name
      operationId: searchMedicines
      parameters:
        - name: name
          in: query
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of matching medicines
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Medicine'

  /medicines/in-stock:
    get:
      tags: [Pharmacy Service]
      summary: Get in-stock medicines
      operationId: getMedicinesInStock
      responses:
        '200':
          description: List of medicines currently in stock
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Medicine'

  # ============================================================
  # BILLING SERVICE (Invoices)
  # ============================================================
  /invoices:
    get:
      tags: [Billing Service]
      summary: Get all invoices
      operationId: getAllInvoices
      responses:
        '200':
          description: List of all invoices
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Invoice'
    post:
      tags: [Billing Service]
      summary: Generate a new invoice
      operationId: createInvoice
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/InvoiceInput'
      responses:
        '201':
          description: Invoice generated successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Invoice'

  /invoices/{id}:
    get:
      tags: [Billing Service]
      summary: Get invoice by ID
      operationId: getInvoiceById
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Invoice details
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Invoice'
        '404':
          description: Invoice not found
    put:
      tags: [Billing Service]
      summary: Update invoice
      operationId: updateInvoice
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/InvoiceInput'
      responses:
        '200':
          description: Invoice updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Invoice'
    delete:
      tags: [Billing Service]
      summary: Delete invoice
      operationId: deleteInvoice
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '204':
          description: Invoice deleted
        '404':
          description: Invoice not found

  /invoices/{id}/pay:
    patch:
      tags: [Billing Service]
      summary: Record payment for invoice
      operationId: recordPayment
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
        - name: paymentMethod
          in: query
          required: true
          schema:
            type: string
            enum: [CASH, CREDIT_CARD, DEBIT_CARD, INSURANCE, ONLINE]
      responses:
        '200':
          description: Payment recorded
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Invoice'

  /invoices/patient/{patientId}:
    get:
      tags: [Billing Service]
      summary: Get invoices by patient
      operationId: getInvoicesByPatient
      parameters:
        - name: patientId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of patient's invoices
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Invoice'

  /invoices/status:
    get:
      tags: [Billing Service]
      summary: Get invoices by payment status
      operationId: getInvoicesByStatus
      parameters:
        - name: status
          in: query
          required: true
          schema:
            type: string
            enum: [PENDING, PAID, OVERDUE, CANCELLED]
      responses:
        '200':
          description: List of invoices with specified status
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Invoice'

  # ============================================================
  # LAB TEST SERVICE
  # ============================================================
  /labtests:
    get:
      tags: [Lab Test Service]
      summary: Get all lab tests
      operationId: getAllLabTests
      responses:
        '200':
          description: List of all lab tests
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/LabTest'
    post:
      tags: [Lab Test Service]
      summary: Order a new lab test
      operationId: createLabTest
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/LabTestInput'
      responses:
        '201':
          description: Lab test ordered successfully
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LabTest'

  /labtests/{id}:
    get:
      tags: [Lab Test Service]
      summary: Get lab test by ID
      operationId: getLabTestById
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Lab test details
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LabTest'
        '404':
          description: Lab test not found
    put:
      tags: [Lab Test Service]
      summary: Update lab test
      operationId: updateLabTest
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/LabTestInput'
      responses:
        '200':
          description: Lab test updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LabTest'
    delete:
      tags: [Lab Test Service]
      summary: Cancel lab test
      operationId: deleteLabTest
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
      responses:
        '204':
          description: Lab test cancelled
        '404':
          description: Lab test not found

  /labtests/{id}/result:
    patch:
      tags: [Lab Test Service]
      summary: Submit lab test result
      operationId: submitLabTestResult
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
        - name: result
          in: query
          required: true
          schema:
            type: string
        - name: notes
          in: query
          required: false
          schema:
            type: string
        - name: completedDate
          in: query
          required: false
          schema:
            type: string
            format: date
      responses:
        '200':
          description: Result submitted
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LabTest'

  /labtests/{id}/status:
    patch:
      tags: [Lab Test Service]
      summary: Update lab test status
      operationId: updateLabTestStatus
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: string
        - name: status
          in: query
          required: true
          schema:
            type: string
            enum: [ORDERED, SAMPLE_COLLECTED, IN_PROGRESS, COMPLETED, CANCELLED]
      responses:
        '200':
          description: Status updated
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LabTest'

  /labtests/patient/{patientId}:
    get:
      tags: [Lab Test Service]
      summary: Get lab tests by patient
      operationId: getLabTestsByPatient
      parameters:
        - name: patientId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of patient's lab tests
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/LabTest'

  /labtests/doctor/{doctorId}:
    get:
      tags: [Lab Test Service]
      summary: Get lab tests by doctor
      operationId: getLabTestsByDoctor
      parameters:
        - name: doctorId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of doctor's ordered lab tests
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/LabTest'

  /labtests/status:
    get:
      tags: [Lab Test Service]
      summary: Get lab tests by status
      operationId: getLabTestsByStatus
      parameters:
        - name: status
          in: query
          required: true
          schema:
            type: string
            enum: [ORDERED, SAMPLE_COLLECTED, IN_PROGRESS, COMPLETED, CANCELLED]
      responses:
        '200':
          description: List of lab tests with specified status
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/LabTest'

  /labtests/category/{category}:
    get:
      tags: [Lab Test Service]
      summary: Get lab tests by category
      operationId: getLabTestsByCategory
      parameters:
        - name: category
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: List of lab tests in category
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/LabTest'

# ============================================================
# COMPONENTS
# ============================================================
components:
  schemas:
    Patient:
      type: object
      properties:
        id:
          type: string
          description: Unique identifier
        code:
          type: string
          description: Patient code (e.g., PAT-001)
        firstName:
          type: string
        lastName:
          type: string
        email:
          type: string
          format: email
        phone:
          type: string
        dateOfBirth:
          type: string
          format: date
        gender:
          type: string
          enum: [MALE, FEMALE, OTHER]
        address:
          type: string
        bloodGroup:
          type: string
          enum: [A+, A-, B+, B-, AB+, AB-, O+, O-]
      example:
        id: "65f1a2b3c4d5e6f7a8b9c0d1"
        code: "PAT-001"
        firstName: "John"
        lastName: "Doe"
        email: "john.doe@email.com"
        phone: "+1-555-123-4567"
        dateOfBirth: "1990-05-15"
        gender: "MALE"
        address: "123 Main St, Springfield"
        bloodGroup: "O+"

    PatientInput:
      type: object
      required:
        - firstName
        - lastName
        - email
        - phone
      properties:
        firstName:
          type: string
        lastName:
          type: string
        email:
          type: string
          format: email
        phone:
          type: string
        dateOfBirth:
          type: string
          format: date
        gender:
          type: string
          enum: [MALE, FEMALE, OTHER]
        address:
          type: string
        bloodGroup:
          type: string
          enum: [A+, A-, B+, B-, AB+, AB-, O+, O-]

    Doctor:
      type: object
      properties:
        id:
          type: string
          description: Unique identifier
        code:
          type: string
          description: Doctor code (e.g., DOC-001)
        firstName:
          type: string
        lastName:
          type: string
        specialization:
          type: string
        email:
          type: string
          format: email
        phone:
          type: string
        licenseNumber:
          type: string
        department:
          type: string
        available:
          type: boolean
      example:
        id: "65f1a2b3c4d5e6f7a8b9c0d2"
        code: "DOC-001"
        firstName: "Sarah"
        lastName: "Smith"
        specialization: "Cardiology"
        email: "sarah.smith@medicore.com"
        phone: "+1-555-987-6543"
        licenseNumber: "MD-12345"
        department: "Cardiology"
        available: true

    DoctorInput:
      type: object
      required:
        - firstName
        - lastName
        - specialization
        - email
        - licenseNumber
      properties:
        firstName:
          type: string
        lastName:
          type: string
        specialization:
          type: string
        email:
          type: string
          format: email
        phone:
          type: string
        licenseNumber:
          type: string
        department:
          type: string
        available:
          type: boolean
          default: true

    Appointment:
      type: object
      properties:
        id:
          type: string
        code:
          type: string
        patientId:
          type: string
        doctorId:
          type: string
        appointmentDate:
          type: string
          format: date
        appointmentTime:
          type: string
        reason:
          type: string
        status:
          type: string
          enum: [SCHEDULED, CONFIRMED, CANCELLED, COMPLETED, NO_SHOW]
        notes:
          type: string
      example:
        id: "65f1a2b3c4d5e6f7a8b9c0d3"
        code: "APT-001"
        patientId: "65f1a2b3c4d5e6f7a8b9c0d1"
        doctorId: "65f1a2b3c4d5e6f7a8b9c0d2"
        appointmentDate: "2024-03-15"
        appointmentTime: "10:30"
        reason: "Annual checkup"
        status: "SCHEDULED"
        notes: "Patient requested morning appointment"

    AppointmentInput:
      type: object
      required:
        - patientId
        - doctorId
        - appointmentDate
        - appointmentTime
      properties:
        patientId:
          type: string
        doctorId:
          type: string
        appointmentDate:
          type: string
          format: date
        appointmentTime:
          type: string
        reason:
          type: string
        notes:
          type: string

    Medicine:
      type: object
      properties:
        id:
          type: string
        code:
          type: string
        name:
          type: string
        genericName:
          type: string
        category:
          type: string
        manufacturer:
          type: string
        price:
          type: number
          format: double
        stockQuantity:
          type: integer
        expiryDate:
          type: string
          format: date
        description:
          type: string
      example:
        id: "65f1a2b3c4d5e6f7a8b9c0d4"
        code: "MED-001"
        name: "Amoxicillin 500mg"
        genericName: "Amoxicillin"
        category: "Antibiotics"
        manufacturer: "PharmaCorp"
        price: 15.99
        stockQuantity: 500
        expiryDate: "2026-12-31"
        description: "Broad-spectrum antibiotic"

    MedicineInput:
      type: object
      required:
        - name
        - category
        - price
      properties:
        name:
          type: string
        genericName:
          type: string
        category:
          type: string
        manufacturer:
          type: string
        price:
          type: number
          format: double
        stockQuantity:
          type: integer
        expiryDate:
          type: string
          format: date
        description:
          type: string

    Invoice:
      type: object
      properties:
        id:
          type: string
        code:
          type: string
        patientId:
          type: string
        appointmentId:
          type: string
        invoiceDate:
          type: string
          format: date
        consultationFee:
          type: number
          format: double
        medicineFee:
          type: number
          format: double
        labFee:
          type: number
          format: double
        totalAmount:
          type: number
          format: double
        paymentStatus:
          type: string
          enum: [PENDING, PAID, OVERDUE, CANCELLED]
        paymentMethod:
          type: string
          enum: [CASH, CREDIT_CARD, DEBIT_CARD, INSURANCE, ONLINE]
      example:
        id: "65f1a2b3c4d5e6f7a8b9c0d5"
        code: "INV-001"
        patientId: "65f1a2b3c4d5e6f7a8b9c0d1"
        appointmentId: "65f1a2b3c4d5e6f7a8b9c0d3"
        invoiceDate: "2024-03-15"
        consultationFee: 150.00
        medicineFee: 45.99
        labFee: 75.00
        totalAmount: 270.99
        paymentStatus: "PENDING"
        paymentMethod: null

    InvoiceInput:
      type: object
      required:
        - patientId
      properties:
        patientId:
          type: string
        appointmentId:
          type: string
        consultationFee:
          type: number
          format: double
        medicineFee:
          type: number
          format: double
        labFee:
          type: number
          format: double

    LabTest:
      type: object
      properties:
        id:
          type: string
        patientId:
          type: string
        doctorId:
          type: string
        testName:
          type: string
        testCode:
          type: string
        category:
          type: string
        orderedDate:
          type: string
          format: date
        completedDate:
          type: string
          format: date
        result:
          type: string
        normalRange:
          type: string
        status:
          type: string
          enum: [ORDERED, SAMPLE_COLLECTED, IN_PROGRESS, COMPLETED, CANCELLED]
        technicianNotes:
          type: string
      example:
        id: "65f1a2b3c4d5e6f7a8b9c0d6"
        patientId: "65f1a2b3c4d5e6f7a8b9c0d1"
        doctorId: "65f1a2b3c4d5e6f7a8b9c0d2"
        testName: "Complete Blood Count"
        testCode: "CBC-001"
        category: "Hematology"
        orderedDate: "2024-03-15"
        completedDate: null
        result: null
        normalRange: "4.5-11.0 x10^9/L"
        status: "ORDERED"
        technicianNotes: null

    LabTestInput:
      type: object
      required:
        - patientId
        - testName
        - category
      properties:
        patientId:
          type: string
        doctorId:
          type: string
        testName:
          type: string
        testCode:
          type: string
        category:
          type: string
        normalRange:
          type: string
        technicianNotes:
          type: string

  securitySchemes:
    bearerAuth:
      type: http
      scheme: bearer
      bearerFormat: JWT
```

---

## 👥 Team Contributions

| Member | Service | Port | Responsibility |
|--------|---------|------|---------------|
| Member 1 | Patient Service | 8081 | Patient model, CRUD endpoints, search, gateway route |
| Member 2 | Doctor Service | 8082 | Doctor model, CRUD, availability, specialization filter, gateway route |
| Member 3 | Appointment Service | 8083 | Appointment model, booking, status management, gateway route |
| Member 4 | Pharmacy Service | 8084 | Medicine inventory, stock updates, category filters, gateway route |
| Member 5 | Billing Service | 8085 | Invoice generation, payment tracking, gateway route |
| Member 6 | Lab Test Service | 8086 | Test ordering, result submission, category filters, gateway route |
| **All** | **API Gateway** | **8080** | **application.yml routing config, Swagger aggregation** |
