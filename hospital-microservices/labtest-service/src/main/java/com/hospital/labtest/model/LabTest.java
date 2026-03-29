package com.hospital.labtest.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "lab_tests")
@Data @NoArgsConstructor @AllArgsConstructor
public class LabTest {
    @Id private String id;
    @Indexed private String patientId;
    @Indexed private String doctorId;
    private String testName;
    private String testCode;
    @Indexed private String category;
    private String orderedDate;
    private String completedDate;
    private String result;
    private String normalRange;
    @Indexed private String status;
    private String technicianNotes;
}
