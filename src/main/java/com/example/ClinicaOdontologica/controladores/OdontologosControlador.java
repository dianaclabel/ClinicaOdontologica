package com.example.ClinicaOdontologica.controladores;

import com.example.ClinicaOdontologica.model.OdontologoDTO;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Collection;



@RestController
@RequestMapping("/api/odontologos")
public class OdontologosControlador {


    @Autowired
    private IOdontologosSevicio odontologosSevicio;
    @PostMapping
    public ResponseEntity<?> agregarOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        odontologosSevicio.registrarOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarOdontologo(@PathVariable Long id,@RequestBody OdontologoDTO odontologoDTO){
        odontologosSevicio.modificarOdontologo(id,odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id){
        odontologosSevicio.eliminarOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Odontologo Eliminado");
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarUno(@PathVariable Long id){
        return odontologosSevicio.buscarUnOdontologo(id);
    }

    @GetMapping("/all")
    public Collection<OdontologoDTO> listarTodos(){
        return odontologosSevicio.buscarTodos();
    }

 /*
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
*/


}
