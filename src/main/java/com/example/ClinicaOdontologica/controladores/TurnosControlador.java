package com.example.ClinicaOdontologica.controladores;

import com.example.ClinicaOdontologica.model.PacienteDTO;
import com.example.ClinicaOdontologica.model.TurnoDTO;
import com.example.ClinicaOdontologica.servicios.IPacientesServicio;
import com.example.ClinicaOdontologica.servicios.ITurnosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/turnos")
public class TurnosControlador {
    @Autowired
    ITurnosServicio turnosServicio;
    @PostMapping
    public ResponseEntity<?> agregarTurno(@RequestBody TurnoDTO turnoDTO){
        turnosServicio.registrarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modificarPaciente(@RequestBody TurnoDTO turnoDTO){
        turnosServicio.modificarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id){
        turnosServicio.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Turno Eliminado");
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarUno(@PathVariable Long id){
        return turnosServicio.buscarUnTurno(id);
    }

    @GetMapping("/all")
    public Collection<TurnoDTO> listarTodos(){
        return turnosServicio.buscarTodos();
    }
}
