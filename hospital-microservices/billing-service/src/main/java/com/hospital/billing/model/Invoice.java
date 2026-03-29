package com.hospital.billing.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "invoices")
@Data @NoArgsConstructor @AllArgsConstructor
public class Invoice {
    @Id private String id;
    @Indexed private String patientId;
    private String appointmentId;
    private String invoiceDate;
    private Double consultationFee;
    private Double medicineFee;
    private Double labFee;
    private Double totalAmount;
    @Indexed private String paymentStatus;
    private String paymentMethod;
}
