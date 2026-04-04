package com.hospital.doctor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "doctors")
@Schema(description = "Doctor entity representing a hospital doctor")
public class Doctor {
    @Schema(description = "Unique identifier", example = "65f1a2b3c4d5e6f7a8b9c0d2")
    @Id
    private String id;
    @Schema(description = "Doctor code", example = "DOC-001")
    private String code;
    @Schema(description = "Doctor's first name", example = "Sarah")
    private String firstName;
    @Schema(description = "Doctor's last name", example = "Smith")
    private String lastName;
    @Schema(description = "Medical specialization", example = "Cardiology")
    @Indexed
    private String specialization;
    @Schema(description = "Email address", example = "sarah.smith@medicore.com")
    private String email;
    @Schema(description = "Phone number", example = "+1-555-987-6543")
    private String phone;
    @Schema(description = "Medical license number", example = "MD-12345")
    private String licenseNumber;
    @Schema(description = "Department", example = "Cardiology")
    private String department;
    @Schema(description = "Availability status", example = "true")
    private boolean available;

    public Doctor() {
    }

    public Doctor(String id, String firstName, String lastName, String specialization, String email, String phone,
            String licenseNumber, String department, boolean available) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.department = department;
        this.available = available;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
