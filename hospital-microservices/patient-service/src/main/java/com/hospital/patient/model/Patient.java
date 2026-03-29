package com.hospital.patient.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "patients")
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true, sparse = true)
    private String email;
    private String phone;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String bloodGroup;
}
