package com.example.ClinicaOdontologica.controladores;

import com.example.ClinicaOdontologica.entidades.Odontologo;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/odontologos")
public class OdontologosControlador {

    @Autowired
    private IOdontologosSevicio odontologosSevicio;

    @GetMapping
    public ResponseEntity<List<Odontologo>> getOdontologos() {
        return ResponseEntity.ok(odontologosSevicio.listar());
    }

}
