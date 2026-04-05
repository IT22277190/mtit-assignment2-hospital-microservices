package com.hospital.pharmacy.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "medicines")
@Schema(description = "Medicine entity for pharmacy inventory")
public class Medicine {
    @Schema(description = "Unique identifier", example = "65f1a2b3c4d5e6f7a8b9c0d4")
    @Id
    private String id;
    @Schema(description = "Medicine code", example = "MED-001")
    private String code;
    @Schema(description = "Medicine name", example = "Amoxicillin 500mg")
    @Indexed
    private String name;
    @Schema(description = "Generic name", example = "Amoxicillin")
    private String genericName;
    @Schema(description = "Category", example = "Antibiotics")
    @Indexed
    private String category;
    @Schema(description = "Manufacturer", example = "PharmaCorp")
    private String manufacturer;
    @Schema(description = "Price", example = "15.99")
    private Double price;
    @Schema(description = "Stock quantity", example = "500")
    private Integer stockQuantity;
    @Schema(description = "Expiry date", example = "2026-12-31")
    private String expiryDate;
    @Schema(description = "Description", example = "Broad-spectrum antibiotic")
    private String description;

    public Medicine() {
    }

    public Medicine(String id, String name, String genericName, String category, String manufacturer, Double price,
            Integer stockQuantity, String expiryDate, String description) {
        this.id = id;
        this.name = name;
        this.genericName = genericName;
        this.category = category;
        this.manufacturer = manufacturer;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.expiryDate = expiryDate;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
