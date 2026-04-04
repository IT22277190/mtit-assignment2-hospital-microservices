package com.hospital.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class OpenApiResourceConfig {

    @Bean
    public RouterFunction<ServerResponse> openApiRouter() {
        String openApiSpec = """
{
  "openapi": "3.0.3",
  "info": {
    "title": "MediCore Hospital Management API",
    "version": "1.0.0",
    "description": "REST API for MediCore Hospital Management System. All endpoints are accessed through the API Gateway at http://localhost:8080/gateway",
    "contact": {
      "name": "MediCore Development Team",
      "url": "http://localhost:8080"
    }
  },
  "servers": [
    {
      "url": "http://localhost:8080/gateway",
      "description": "API Gateway Base"
    }
  ],
  "tags": [
    {"name": "Patient Service", "description": "Patient registration and management"},
    {"name": "Doctor Service", "description": "Doctor profiles and availability"},
    {"name": "Appointment Service", "description": "Appointment scheduling and management"},
    {"name": "Pharmacy Service", "description": "Medicine inventory and dispensing"},
    {"name": "Billing Service", "description": "Invoice generation and payment processing"},
    {"name": "Lab Test Service", "description": "Laboratory test ordering and results"}
  ],
  "paths": {
    "/patients": {
      "get": {
        "tags": ["Patient Service"],
        "summary": "Get all patients",
        "operationId": "getAllPatients",
        "responses": {"200": {"description": "List of all patients", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Patient"}}}}}}
      },
      "post": {
        "tags": ["Patient Service"],
        "summary": "Register a new patient",
        "operationId": "createPatient",
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/PatientInput"}}}},
        "responses": {"201": {"description": "Patient created successfully", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Patient"}}}}}
      }
    },
    "/patients/{id}": {
      "get": {
        "tags": ["Patient Service"],
        "summary": "Get patient by ID",
        "operationId": "getPatientById",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "Patient details", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Patient"}}}}}
      },
      "put": {
        "tags": ["Patient Service"],
        "summary": "Update patient",
        "operationId": "updatePatient",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/PatientInput"}}}},
        "responses": {"200": {"description": "Patient updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Patient"}}}}}
      },
      "delete": {
        "tags": ["Patient Service"],
        "summary": "Delete patient",
        "operationId": "deletePatient",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"204": {"description": "Patient deleted"}}
      }
    },
    "/patients/search": {
      "get": {
        "tags": ["Patient Service"],
        "summary": "Search patients by last name",
        "operationId": "searchPatientsByLastName",
        "parameters": [{"name": "lastName", "in": "query", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of matching patients", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Patient"}}}}}}
      }
    },
    "/doctors": {
      "get": {
        "tags": ["Doctor Service"],
        "summary": "Get all doctors",
        "operationId": "getAllDoctors",
        "responses": {"200": {"description": "List of all doctors", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Doctor"}}}}}}
      },
      "post": {
        "tags": ["Doctor Service"],
        "summary": "Add a new doctor",
        "operationId": "createDoctor",
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/DoctorInput"}}}},
        "responses": {"201": {"description": "Doctor added successfully", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Doctor"}}}}}
      }
    },
    "/doctors/{id}": {
      "get": {
        "tags": ["Doctor Service"],
        "summary": "Get doctor by ID",
        "operationId": "getDoctorById",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "Doctor details", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Doctor"}}}}}
      },
      "put": {
        "tags": ["Doctor Service"],
        "summary": "Update doctor",
        "operationId": "updateDoctor",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/DoctorInput"}}}},
        "responses": {"200": {"description": "Doctor updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Doctor"}}}}}
      },
      "delete": {
        "tags": ["Doctor Service"],
        "summary": "Remove doctor",
        "operationId": "deleteDoctor",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"204": {"description": "Doctor removed"}}
      }
    },
    "/doctors/specialization/{specialization}": {
      "get": {
        "tags": ["Doctor Service"],
        "summary": "Find doctors by specialization",
        "operationId": "getDoctorsBySpecialization",
        "parameters": [{"name": "specialization", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of doctors with matching specialization", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Doctor"}}}}}}
      }
    },
    "/doctors/available": {
      "get": {
        "tags": ["Doctor Service"],
        "summary": "Get available doctors",
        "operationId": "getAvailableDoctors",
        "responses": {"200": {"description": "List of available doctors", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Doctor"}}}}}}
      }
    },
    "/appointments": {
      "get": {
        "tags": ["Appointment Service"],
        "summary": "Get all appointments",
        "operationId": "getAllAppointments",
        "responses": {"200": {"description": "List of all appointments", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Appointment"}}}}}}
      },
      "post": {
        "tags": ["Appointment Service"],
        "summary": "Book a new appointment",
        "operationId": "createAppointment",
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/AppointmentInput"}}}},
        "responses": {"201": {"description": "Appointment booked successfully", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Appointment"}}}}}
      }
    },
    "/appointments/{id}": {
      "get": {
        "tags": ["Appointment Service"],
        "summary": "Get appointment by ID",
        "operationId": "getAppointmentById",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "Appointment details", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Appointment"}}}}}
      },
      "put": {
        "tags": ["Appointment Service"],
        "summary": "Update appointment",
        "operationId": "updateAppointment",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/AppointmentInput"}}}},
        "responses": {"200": {"description": "Appointment updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Appointment"}}}}}
      },
      "delete": {
        "tags": ["Appointment Service"],
        "summary": "Cancel appointment",
        "operationId": "deleteAppointment",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"204": {"description": "Appointment cancelled"}}
      }
    },
    "/appointments/{id}/status": {
      "patch": {
        "tags": ["Appointment Service"],
        "summary": "Update appointment status",
        "operationId": "updateAppointmentStatus",
        "parameters": [
          {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}},
          {"name": "status", "in": "query", "required": true, "schema": {"type": "string", "enum": ["SCHEDULED", "CONFIRMED", "CANCELLED", "COMPLETED", "NO_SHOW"]}}
        ],
        "responses": {"200": {"description": "Status updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Appointment"}}}}}
      }
    },
    "/appointments/patient/{patientId}": {
      "get": {
        "tags": ["Appointment Service"],
        "summary": "Get appointments by patient",
        "operationId": "getAppointmentsByPatient",
        "parameters": [{"name": "patientId", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of patient's appointments", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Appointment"}}}}}}
      }
    },
    "/appointments/doctor/{doctorId}": {
      "get": {
        "tags": ["Appointment Service"],
        "summary": "Get appointments by doctor",
        "operationId": "getAppointmentsByDoctor",
        "parameters": [{"name": "doctorId", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of doctor's appointments", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Appointment"}}}}}}
      }
    },
    "/appointments/date": {
      "get": {
        "tags": ["Appointment Service"],
        "summary": "Get appointments by date",
        "operationId": "getAppointmentsByDate",
        "parameters": [{"name": "date", "in": "query", "required": true, "schema": {"type": "string", "format": "date"}}],
        "responses": {"200": {"description": "List of appointments on specified date", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Appointment"}}}}}}
      }
    },
    "/medicines": {
      "get": {
        "tags": ["Pharmacy Service"],
        "summary": "Get all medicines",
        "operationId": "getAllMedicines",
        "responses": {"200": {"description": "List of all medicines", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Medicine"}}}}}}
      },
      "post": {
        "tags": ["Pharmacy Service"],
        "summary": "Add a new medicine",
        "operationId": "createMedicine",
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/MedicineInput"}}}},
        "responses": {"201": {"description": "Medicine added successfully", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Medicine"}}}}}
      }
    },
    "/medicines/{id}": {
      "get": {
        "tags": ["Pharmacy Service"],
        "summary": "Get medicine by ID",
        "operationId": "getMedicineById",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "Medicine details", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Medicine"}}}}}
      },
      "put": {
        "tags": ["Pharmacy Service"],
        "summary": "Update medicine",
        "operationId": "updateMedicine",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/MedicineInput"}}}},
        "responses": {"200": {"description": "Medicine updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Medicine"}}}}}
      },
      "delete": {
        "tags": ["Pharmacy Service"],
        "summary": "Remove medicine",
        "operationId": "deleteMedicine",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"204": {"description": "Medicine removed"}}
      }
    },
    "/medicines/{id}/stock": {
      "patch": {
        "tags": ["Pharmacy Service"],
        "summary": "Update medicine stock",
        "operationId": "updateMedicineStock",
        "parameters": [
          {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}},
          {"name": "quantity", "in": "query", "required": true, "schema": {"type": "integer"}}
        ],
        "responses": {"200": {"description": "Stock updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Medicine"}}}}}
      }
    },
    "/medicines/category/{category}": {
      "get": {
        "tags": ["Pharmacy Service"],
        "summary": "Get medicines by category",
        "operationId": "getMedicinesByCategory",
        "parameters": [{"name": "category", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of medicines in category", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Medicine"}}}}}}
      }
    },
    "/medicines/search": {
      "get": {
        "tags": ["Pharmacy Service"],
        "summary": "Search medicines by name",
        "operationId": "searchMedicines",
        "parameters": [{"name": "name", "in": "query", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of matching medicines", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Medicine"}}}}}}
      }
    },
    "/medicines/in-stock": {
      "get": {
        "tags": ["Pharmacy Service"],
        "summary": "Get in-stock medicines",
        "operationId": "getMedicinesInStock",
        "responses": {"200": {"description": "List of medicines currently in stock", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Medicine"}}}}}}
      }
    },
    "/invoices": {
      "get": {
        "tags": ["Billing Service"],
        "summary": "Get all invoices",
        "operationId": "getAllInvoices",
        "responses": {"200": {"description": "List of all invoices", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Invoice"}}}}}}
      },
      "post": {
        "tags": ["Billing Service"],
        "summary": "Generate a new invoice",
        "operationId": "createInvoice",
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/InvoiceInput"}}}},
        "responses": {"201": {"description": "Invoice generated successfully", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Invoice"}}}}}
      }
    },
    "/invoices/{id}": {
      "get": {
        "tags": ["Billing Service"],
        "summary": "Get invoice by ID",
        "operationId": "getInvoiceById",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "Invoice details", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Invoice"}}}}}
      },
      "put": {
        "tags": ["Billing Service"],
        "summary": "Update invoice",
        "operationId": "updateInvoice",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/InvoiceInput"}}}},
        "responses": {"200": {"description": "Invoice updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Invoice"}}}}}
      },
      "delete": {
        "tags": ["Billing Service"],
        "summary": "Delete invoice",
        "operationId": "deleteInvoice",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"204": {"description": "Invoice deleted"}}
      }
    },
    "/invoices/{id}/pay": {
      "patch": {
        "tags": ["Billing Service"],
        "summary": "Record payment for invoice",
        "operationId": "recordPayment",
        "parameters": [
          {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}},
          {"name": "paymentMethod", "in": "query", "required": true, "schema": {"type": "string", "enum": ["CASH", "CREDIT_CARD", "DEBIT_CARD", "INSURANCE", "ONLINE"]}}
        ],
        "responses": {"200": {"description": "Payment recorded", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/Invoice"}}}}}
      }
    },
    "/invoices/patient/{patientId}": {
      "get": {
        "tags": ["Billing Service"],
        "summary": "Get invoices by patient",
        "operationId": "getInvoicesByPatient",
        "parameters": [{"name": "patientId", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of patient's invoices", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Invoice"}}}}}}
      }
    },
    "/invoices/status": {
      "get": {
        "tags": ["Billing Service"],
        "summary": "Get invoices by payment status",
        "operationId": "getInvoicesByStatus",
        "parameters": [{"name": "status", "in": "query", "required": true, "schema": {"type": "string", "enum": ["PENDING", "PAID", "OVERDUE", "CANCELLED"]}}],
        "responses": {"200": {"description": "List of invoices with specified status", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/Invoice"}}}}}}
      }
    },
    "/labtests": {
      "get": {
        "tags": ["Lab Test Service"],
        "summary": "Get all lab tests",
        "operationId": "getAllLabTests",
        "responses": {"200": {"description": "List of all lab tests", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/LabTest"}}}}}}
      },
      "post": {
        "tags": ["Lab Test Service"],
        "summary": "Order a new lab test",
        "operationId": "createLabTest",
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTestInput"}}}},
        "responses": {"201": {"description": "Lab test ordered successfully", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTest"}}}}}
      }
    },
    "/labtests/{id}": {
      "get": {
        "tags": ["Lab Test Service"],
        "summary": "Get lab test by ID",
        "operationId": "getLabTestById",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "Lab test details", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTest"}}}}}
      },
      "put": {
        "tags": ["Lab Test Service"],
        "summary": "Update lab test",
        "operationId": "updateLabTest",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "requestBody": {"required": true, "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTestInput"}}}},
        "responses": {"200": {"description": "Lab test updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTest"}}}}}
      },
      "delete": {
        "tags": ["Lab Test Service"],
        "summary": "Cancel lab test",
        "operationId": "deleteLabTest",
        "parameters": [{"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"204": {"description": "Lab test cancelled"}}
      }
    },
    "/labtests/{id}/result": {
      "patch": {
        "tags": ["Lab Test Service"],
        "summary": "Submit lab test result",
        "operationId": "submitLabTestResult",
        "parameters": [
          {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}},
          {"name": "result", "in": "query", "required": true, "schema": {"type": "string"}},
          {"name": "notes", "in": "query", "required": false, "schema": {"type": "string"}},
          {"name": "completedDate", "in": "query", "required": false, "schema": {"type": "string", "format": "date"}}
        ],
        "responses": {"200": {"description": "Result submitted", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTest"}}}}}
      }
    },
    "/labtests/{id}/status": {
      "patch": {
        "tags": ["Lab Test Service"],
        "summary": "Update lab test status",
        "operationId": "updateLabTestStatus",
        "parameters": [
          {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}},
          {"name": "status", "in": "query", "required": true, "schema": {"type": "string", "enum": ["ORDERED", "SAMPLE_COLLECTED", "IN_PROGRESS", "COMPLETED", "CANCELLED"]}}
        ],
        "responses": {"200": {"description": "Status updated", "content": {"application/json": {"schema": {"$ref": "#/components/schemas/LabTest"}}}}}
      }
    },
    "/labtests/patient/{patientId}": {
      "get": {
        "tags": ["Lab Test Service"],
        "summary": "Get lab tests by patient",
        "operationId": "getLabTestsByPatient",
        "parameters": [{"name": "patientId", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of patient's lab tests", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/LabTest"}}}}}}
      }
    },
    "/labtests/doctor/{doctorId}": {
      "get": {
        "tags": ["Lab Test Service"],
        "summary": "Get lab tests by doctor",
        "operationId": "getLabTestsByDoctor",
        "parameters": [{"name": "doctorId", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of doctor's ordered lab tests", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/LabTest"}}}}}}
      }
    },
    "/labtests/status": {
      "get": {
        "tags": ["Lab Test Service"],
        "summary": "Get lab tests by status",
        "operationId": "getLabTestsByStatus",
        "parameters": [{"name": "status", "in": "query", "required": true, "schema": {"type": "string", "enum": ["ORDERED", "SAMPLE_COLLECTED", "IN_PROGRESS", "COMPLETED", "CANCELLED"]}}],
        "responses": {"200": {"description": "List of lab tests with specified status", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/LabTest"}}}}}}
      }
    },
    "/labtests/category/{category}": {
      "get": {
        "tags": ["Lab Test Service"],
        "summary": "Get lab tests by category",
        "operationId": "getLabTestsByCategory",
        "parameters": [{"name": "category", "in": "path", "required": true, "schema": {"type": "string"}}],
        "responses": {"200": {"description": "List of lab tests in category", "content": {"application/json": {"schema": {"type": "array", "items": {"$ref": "#/components/schemas/LabTest"}}}}}}
      }
    }
  },
  "components": {
    "schemas": {
      "Patient": {
        "type": "object",
        "properties": {
          "id": {"type": "string", "description": "Unique identifier"},
          "code": {"type": "string", "description": "Patient code (e.g., PAT-001)"},
          "firstName": {"type": "string"},
          "lastName": {"type": "string"},
          "email": {"type": "string", "format": "email"},
          "phone": {"type": "string"},
          "dateOfBirth": {"type": "string", "format": "date"},
          "gender": {"type": "string", "enum": ["MALE", "FEMALE", "OTHER"]},
          "address": {"type": "string"},
          "bloodGroup": {"type": "string", "enum": ["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"]}
        }
      },
      "PatientInput": {
        "type": "object",
        "required": ["firstName", "lastName", "email", "phone"],
        "properties": {
          "firstName": {"type": "string"},
          "lastName": {"type": "string"},
          "email": {"type": "string", "format": "email"},
          "phone": {"type": "string"},
          "dateOfBirth": {"type": "string", "format": "date"},
          "gender": {"type": "string", "enum": ["MALE", "FEMALE", "OTHER"]},
          "address": {"type": "string"},
          "bloodGroup": {"type": "string", "enum": ["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"]}
        }
      },
      "Doctor": {
        "type": "object",
        "properties": {
          "id": {"type": "string"},
          "code": {"type": "string"},
          "firstName": {"type": "string"},
          "lastName": {"type": "string"},
          "specialization": {"type": "string"},
          "email": {"type": "string", "format": "email"},
          "phone": {"type": "string"},
          "licenseNumber": {"type": "string"},
          "department": {"type": "string"},
          "available": {"type": "boolean"}
        }
      },
      "DoctorInput": {
        "type": "object",
        "required": ["firstName", "lastName", "specialization", "email", "licenseNumber"],
        "properties": {
          "firstName": {"type": "string"},
          "lastName": {"type": "string"},
          "specialization": {"type": "string"},
          "email": {"type": "string", "format": "email"},
          "phone": {"type": "string"},
          "licenseNumber": {"type": "string"},
          "department": {"type": "string"},
          "available": {"type": "boolean", "default": true}
        }
      },
      "Appointment": {
        "type": "object",
        "properties": {
          "id": {"type": "string"},
          "code": {"type": "string"},
          "patientId": {"type": "string"},
          "doctorId": {"type": "string"},
          "appointmentDate": {"type": "string", "format": "date"},
          "appointmentTime": {"type": "string"},
          "reason": {"type": "string"},
          "status": {"type": "string", "enum": ["SCHEDULED", "CONFIRMED", "CANCELLED", "COMPLETED", "NO_SHOW"]},
          "notes": {"type": "string"}
        }
      },
      "AppointmentInput": {
        "type": "object",
        "required": ["patientId", "doctorId", "appointmentDate", "appointmentTime"],
        "properties": {
          "patientId": {"type": "string"},
          "doctorId": {"type": "string"},
          "appointmentDate": {"type": "string", "format": "date"},
          "appointmentTime": {"type": "string"},
          "reason": {"type": "string"},
          "notes": {"type": "string"}
        }
      },
      "Medicine": {
        "type": "object",
        "properties": {
          "id": {"type": "string"},
          "code": {"type": "string"},
          "name": {"type": "string"},
          "genericName": {"type": "string"},
          "category": {"type": "string"},
          "manufacturer": {"type": "string"},
          "price": {"type": "number", "format": "double"},
          "stockQuantity": {"type": "integer"},
          "expiryDate": {"type": "string", "format": "date"},
          "description": {"type": "string"}
        }
      },
      "MedicineInput": {
        "type": "object",
        "required": ["name", "category", "price"],
        "properties": {
          "name": {"type": "string"},
          "genericName": {"type": "string"},
          "category": {"type": "string"},
          "manufacturer": {"type": "string"},
          "price": {"type": "number", "format": "double"},
          "stockQuantity": {"type": "integer"},
          "expiryDate": {"type": "string", "format": "date"},
          "description": {"type": "string"}
        }
      },
      "Invoice": {
        "type": "object",
        "properties": {
          "id": {"type": "string"},
          "code": {"type": "string"},
          "patientId": {"type": "string"},
          "appointmentId": {"type": "string"},
          "invoiceDate": {"type": "string", "format": "date"},
          "consultationFee": {"type": "number", "format": "double"},
          "medicineFee": {"type": "number", "format": "double"},
          "labFee": {"type": "number", "format": "double"},
          "totalAmount": {"type": "number", "format": "double"},
          "paymentStatus": {"type": "string", "enum": ["PENDING", "PAID", "OVERDUE", "CANCELLED"]},
          "paymentMethod": {"type": "string", "enum": ["CASH", "CREDIT_CARD", "DEBIT_CARD", "INSURANCE", "ONLINE"]}
        }
      },
      "InvoiceInput": {
        "type": "object",
        "required": ["patientId"],
        "properties": {
          "patientId": {"type": "string"},
          "appointmentId": {"type": "string"},
          "consultationFee": {"type": "number", "format": "double"},
          "medicineFee": {"type": "number", "format": "double"},
          "labFee": {"type": "number", "format": "double"}
        }
      },
      "LabTest": {
        "type": "object",
        "properties": {
          "id": {"type": "string"},
          "patientId": {"type": "string"},
          "doctorId": {"type": "string"},
          "testName": {"type": "string"},
          "testCode": {"type": "string"},
          "category": {"type": "string"},
          "orderedDate": {"type": "string", "format": "date"},
          "completedDate": {"type": "string", "format": "date"},
          "result": {"type": "string"},
          "normalRange": {"type": "string"},
          "status": {"type": "string", "enum": ["ORDERED", "SAMPLE_COLLECTED", "IN_PROGRESS", "COMPLETED", "CANCELLED"]},
          "technicianNotes": {"type": "string"}
        }
      },
      "LabTestInput": {
        "type": "object",
        "required": ["patientId", "testName", "category"],
        "properties": {
          "patientId": {"type": "string"},
          "doctorId": {"type": "string"},
          "testName": {"type": "string"},
          "testCode": {"type": "string"},
          "category": {"type": "string"},
          "normalRange": {"type": "string"},
          "technicianNotes": {"type": "string"}
        }
      }
    }
  }
}
""";

        return RouterFunctions.route()
                .GET("/v3/api-docs/all-services", request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(openApiSpec))
                .GET("/api-docs/all-services", request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(openApiSpec))
                .build();
    }
}
