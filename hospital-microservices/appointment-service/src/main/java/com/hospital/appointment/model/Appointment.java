package com.hospital.appointment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "appointments")
@Schema(description = "Appointment entity for scheduling patient visits")
public class Appointment {
    @Schema(description = "Unique identifier", example = "65f1a2b3c4d5e6f7a8b9c0d3")
    @Id
    private String id;
    @Schema(description = "Appointment code", example = "APT-001")
    private String code;
    @Schema(description = "Patient ID reference", example = "65f1a2b3c4d5e6f7a8b9c0d1")
    @Indexed
    private String patientId;
    @Schema(description = "Doctor ID reference", example = "65f1a2b3c4d5e6f7a8b9c0d2")
    @Indexed
    private String doctorId;
    @Schema(description = "Appointment date", example = "2024-03-15")
    private String appointmentDate;
    @Schema(description = "Appointment time", example = "10:30")
    private String appointmentTime;
    @Schema(description = "Reason for visit", example = "Annual checkup")
    private String reason;
    @Schema(description = "Status: SCHEDULED, CONFIRMED, CANCELLED, COMPLETED, NO_SHOW", example = "SCHEDULED")
    private String status;
    @Schema(description = "Additional notes", example = "Patient requested morning appointment")
    private String notes;

    public Appointment() {
    }

    public Appointment(String id, String patientId, String doctorId, String appointmentDate, String appointmentTime,
            String reason, String status, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
