package com.hospital.pharmacy.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "medicines")
@Data @NoArgsConstructor @AllArgsConstructor
public class Medicine {
    @Id private String id;
    @Indexed private String name;
    private String genericName;
    @Indexed private String category;
    private String manufacturer;
    private Double price;
    private Integer stockQuantity;
    private String expiryDate;
    private String description;
}
