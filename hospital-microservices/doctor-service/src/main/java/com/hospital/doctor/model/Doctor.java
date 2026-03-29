package com.hospital.doctor.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctors")
@Data @NoArgsConstructor @AllArgsConstructor
public class Doctor {
    @Id private String id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private String licenseNumber;
    private String department;
    private boolean available;
}
