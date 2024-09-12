package com.example.ClinicaOdontologica.controladores;

import com.example.ClinicaOdontologica.dto.OdontologoCreateDTO;
import com.example.ClinicaOdontologica.dto.OdontologoUpdateDTO;
import com.example.ClinicaOdontologica.entidades.Odontologo;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologo(@Valid @RequestBody OdontologoCreateDTO payload) {
        return ResponseEntity.ok(odontologosSevicio.registrar(payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        System.out.println("Buscando odontologo por id: " + id);
        return ResponseEntity.ok(odontologosSevicio.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Long id) {
        odontologosSevicio.borrar(id);
        return ResponseEntity.ok("Odontologo borrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Long id, @RequestBody OdontologoUpdateDTO odontologo) {
        return ResponseEntity.ok(odontologosSevicio.actualizar(id, odontologo));
    }



}
