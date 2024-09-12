package com.example.ClinicaOdontologica.controladores;

import com.example.ClinicaOdontologica.model.PacienteDTO;
import com.example.ClinicaOdontologica.servicios.IPacientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesControlador {
    @Autowired
    IPacientesServicio pacientesServicio;

    @PostMapping
    public ResponseEntity<?> agregarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacientesServicio.registrarPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modificarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacientesServicio.modificarPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id){
        pacientesServicio.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente Eliminado");
    }

    @GetMapping("/{id}")
    public PacienteDTO buscarUno(@PathVariable Long id){
        return pacientesServicio.buscarUnPaciente(id);
    }

    @GetMapping("/all")
    public Collection<PacienteDTO> listarTodos(){
        return pacientesServicio.buscarTodos();
    }
}
