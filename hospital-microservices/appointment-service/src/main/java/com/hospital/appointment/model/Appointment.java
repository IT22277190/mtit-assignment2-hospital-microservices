package com.hospital.appointment.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "appointments")
@Data @NoArgsConstructor @AllArgsConstructor
public class Appointment {
    @Id private String id;
    @Indexed private String patientId;
    @Indexed private String doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private String reason;
    private String status;
    private String notes;
}
