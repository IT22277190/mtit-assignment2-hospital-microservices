package com.hospital.labtest.controller;
import com.hospital.labtest.model.LabTest;
import com.hospital.labtest.service.LabTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/labtests")
@Tag(name = "Lab Test API", description = "Manage lab tests — backed by MongoDB")
public class LabTestController {
    @Autowired private LabTestService svc;
    @GetMapping @Operation(summary="Get all lab tests")
    public ResponseEntity<List<LabTest>> getAll() { return ResponseEntity.ok(svc.getAll()); }
    @GetMapping("/{id}") @Operation(summary="Get lab test by ID")
    public ResponseEntity<LabTest> getById(@PathVariable String id) {
        return svc.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping @Operation(summary="Order lab test")
    public ResponseEntity<LabTest> create(@RequestBody LabTest t) {
        return ResponseEntity.status(HttpStatus.CREATED).body(svc.create(t)); }
    @PutMapping("/{id}") @Operation(summary="Update lab test")
    public ResponseEntity<LabTest> update(@PathVariable String id, @RequestBody LabTest t) {
        return svc.update(id, t).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PatchMapping("/{id}/result")
    @Operation(summary="Submit result", description="Updates the lab test result. The `result` and `completedDate` query parameters are required; `notes` is optional.")
    public ResponseEntity<LabTest> submitResult(@PathVariable String id,
            @Parameter(description = "Final lab result", example = "Normal")
            @RequestParam String result,
            @Parameter(description = "Optional technician notes", example = "Sample processed successfully")
            @RequestParam(required = false) String notes,
            @Parameter(description = "Completion date in YYYY-MM-DD format", example = "2026-04-04")
            @RequestParam String completedDate) {
        return svc.updateResult(id, result, notes, completedDate)
                  .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PatchMapping("/{id}/status") @Operation(summary="Update status")
    public ResponseEntity<LabTest> updateStatus(@PathVariable String id, @RequestParam String status) {
        return svc.updateStatus(id, status).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}") @Operation(summary="Cancel test")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return svc.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); }
    @GetMapping("/patient/{pid}") @Operation(summary="Tests by patient")
    public ResponseEntity<List<LabTest>> byPatient(@PathVariable String pid) {
        return ResponseEntity.ok(svc.byPatient(pid)); }
    
    @GetMapping("/doctor/{did}") 
    @Operation(summary="Tests by doctor")
    public ResponseEntity<List<LabTest>> byDoctor(@PathVariable String did) 
    {
        return ResponseEntity.ok(svc.byDoctor(did)); 
        }
   
    @GetMapping("/status") 
    @Operation(summary="Tests by status")
    public ResponseEntity<List<LabTest>> byStatus(@RequestParam String status) 
    {
        return ResponseEntity.ok(svc.byStatus(status)); 
        }
    
    @GetMapping("/category/{cat}") 
    @Operation(summary="Tests by category")
    public ResponseEntity<List<LabTest>> byCategory(@PathVariable String cat) 
    {
        return ResponseEntity.ok(svc.byCategory(cat)); 
    }
}
