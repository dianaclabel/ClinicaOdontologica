package com.example.ClinicaOdontologica.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PacientesControlador {

    @GetMapping
    public ResponseEntity<String> getPatients() {
        return ResponseEntity.ok("Patients");
    }


}
