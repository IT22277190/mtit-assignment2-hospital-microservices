package com.hospital.labtest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "lab_tests")
@Schema(description = "Lab Test entity for laboratory management")
public class LabTest {
    @Schema(description = "Unique identifier", example = "65f1a2b3c4d5e6f7a8b9c0d6")
    @Id
    private String id;
    @Schema(description = "Patient ID reference", example = "65f1a2b3c4d5e6f7a8b9c0d1")
    @Indexed
    private String patientId;
    @Schema(description = "Doctor ID reference", example = "65f1a2b3c4d5e6f7a8b9c0d2")
    @Indexed
    private String doctorId;
    @Schema(description = "Test name", example = "Complete Blood Count")
    private String testName;
    @Schema(description = "Test code", example = "CBC-001")
    private String testCode;
    @Schema(description = "Test category", example = "Hematology")
    @Indexed
    private String category;
    @Schema(description = "Date ordered", example = "2024-03-15")
    private String orderedDate;
    @Schema(description = "Date completed", example = "2024-03-16")
    private String completedDate;
    @Schema(description = "Test result", example = "Normal")
    private String result;
    @Schema(description = "Normal range reference", example = "4.5-11.0 x10^9/L")
    private String normalRange;
    @Schema(description = "Status: ORDERED, SAMPLE_COLLECTED, IN_PROGRESS, COMPLETED, CANCELLED", example = "ORDERED")
    @Indexed
    private String status;
    @Schema(description = "Technician notes", example = "Sample processed successfully")
    private String technicianNotes;

    public LabTest() {
    }

    public LabTest(String id, String patientId, String doctorId, String testName, String testCode, String category,
            String orderedDate, String completedDate, String result, String normalRange, String status,
            String technicianNotes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.testName = testName;
        this.testCode = testCode;
        this.category = category;
        this.orderedDate = orderedDate;
        this.completedDate = completedDate;
        this.result = result;
        this.normalRange = normalRange;
        this.status = status;
        this.technicianNotes = technicianNotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(String normalRange) {
        this.normalRange = normalRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTechnicianNotes() {
        return technicianNotes;
    }

    public void setTechnicianNotes(String technicianNotes) {
        this.technicianNotes = technicianNotes;
    }
}
