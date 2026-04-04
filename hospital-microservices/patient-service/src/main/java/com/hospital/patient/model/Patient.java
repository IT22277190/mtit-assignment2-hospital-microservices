package com.hospital.patient.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "patients")
@Schema(description = "Patient entity representing a hospital patient")
public class Patient {
    @Schema(description = "Unique identifier of the patient", example = "65f1a2b3c4d5e6f7a8b9c0d1")
    @Id
    private String id;
    @Schema(description = "Patient code", example = "PAT-001")
    private String code;
    @Schema(description = "Patient's first name", example = "John")
    private String firstName;
    @Schema(description = "Patient's last name", example = "Doe")
    private String lastName;
    @Schema(description = "Patient's email address", example = "john.doe@email.com")
    @Indexed(unique = true, sparse = true)
    private String email;
    @Schema(description = "Patient's phone number", example = "+1-555-123-4567")
    private String phone;
    @Schema(description = "Date of birth", example = "1990-05-15")
    private String dateOfBirth;
    @Schema(description = "Gender", example = "MALE")
    private String gender;
    @Schema(description = "Home address", example = "123 Main St, Springfield")
    private String address;
    @Schema(description = "Blood group", example = "O+")
    private String bloodGroup;

    public Patient() {
    }

    public Patient(String id, String firstName, String lastName, String email, String phone, String dateOfBirth,
            String gender, String address, String bloodGroup) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.bloodGroup = bloodGroup;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
