package com.hospital.billing.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "invoices")
@Schema(description = "Invoice entity for billing")
public class Invoice {
    @Schema(description = "Unique identifier", example = "65f1a2b3c4d5e6f7a8b9c0d5")
    @Id
    private String id;
    @Schema(description = "Invoice code", example = "INV-001")
    private String code;
    @Schema(description = "Patient ID reference", example = "65f1a2b3c4d5e6f7a8b9c0d1")
    @Indexed
    private String patientId;
    @Schema(description = "Appointment ID reference", example = "65f1a2b3c4d5e6f7a8b9c0d3")
    private String appointmentId;
    @Schema(description = "Invoice date", example = "2024-03-15")
    private String invoiceDate;
    @Schema(description = "Consultation fee", example = "150.00")
    private Double consultationFee;
    @Schema(description = "Medicine fee", example = "45.99")
    private Double medicineFee;
    @Schema(description = "Lab fee", example = "75.00")
    private Double labFee;
    @Schema(description = "Total amount", example = "270.99")
    private Double totalAmount;
    @Schema(description = "Payment status: PENDING, PAID, OVERDUE, CANCELLED", example = "PENDING")
    @Indexed
    private String paymentStatus;
    @Schema(description = "Payment method: CASH, CREDIT_CARD, DEBIT_CARD, INSURANCE, ONLINE", example = "CREDIT_CARD")
    private String paymentMethod;

    public Invoice() {
    }

    public Invoice(String id, String patientId, String appointmentId, String invoiceDate, Double consultationFee,
            Double medicineFee, Double labFee, Double totalAmount, String paymentStatus, String paymentMethod) {
        this.id = id;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.invoiceDate = invoiceDate;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.labFee = labFee;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
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

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public Double getMedicineFee() {
        return medicineFee;
    }

    public void setMedicineFee(Double medicineFee) {
        this.medicineFee = medicineFee;
    }

    public Double getLabFee() {
        return labFee;
    }

    public void setLabFee(Double labFee) {
        this.labFee = labFee;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
