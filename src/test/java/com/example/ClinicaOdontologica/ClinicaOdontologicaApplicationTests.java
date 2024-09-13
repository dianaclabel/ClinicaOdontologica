package com.example.ClinicaOdontologica;

import com.example.ClinicaOdontologica.model.OdontologoDTO;
import com.example.ClinicaOdontologica.model.PacienteDTO;
import com.example.ClinicaOdontologica.model.TurnoDTO;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;
import com.example.ClinicaOdontologica.servicios.IPacientesServicio;
import com.example.ClinicaOdontologica.servicios.ITurnosServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ClinicaOdontologicaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOdontologosSevicio odontologosSevicio;
    @MockBean
    private IPacientesServicio pacientesServicio;

    @Autowired
    private ITurnosServicio turnosServicio;
    @Test
    void testAgregarOdontologo() throws Exception {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Pérez");
        odontologo.setMatricula("1234");

        when(odontologosSevicio.registrarOdontologo(any(OdontologoDTO.class)))
                .thenReturn(odontologo);

        mockMvc.perform(post("/api/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan\",\"apellido\":\"Pérez\",\"matricula\":\"1234\"}"))
                .andExpect(status().isOk());
    }


    @Test
    void testEliminarOdontologo() throws Exception {
        doNothing().when(odontologosSevicio).eliminarOdontologo(1L);

        mockMvc.perform(delete("/api/odontologos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Odontologo Eliminado"));
    }

    @Test
    void testAgregarPaciente() throws Exception {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre("María");
        paciente.setApellido("González");
        paciente.setDni("98765432");

        when(pacientesServicio.registrarPaciente(any(PacienteDTO.class)))
                .thenReturn(paciente);

        mockMvc.perform(post("/api/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"María\",\"apellido\":\"González\",\"dni\":\"98765432\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarPaciente() throws Exception {
        doNothing().when(pacientesServicio).eliminarPaciente(1L);

        mockMvc.perform(delete("/api/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Paciente Eliminado"));
    }

    @Test
    void testModificarPaciente() throws Exception {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre("María");
        paciente.setApellido("González");
        paciente.setDni("98765432");

        when(pacientesServicio.modificarPaciente(any(Long.class), any(PacienteDTO.class)))
                .thenReturn(paciente);

        mockMvc.perform(put("/api/pacientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"María Modificada\",\"apellido\":\"González Modificada\",\"dni\":\"98765432\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarUno() throws Exception {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setNombre("María");
        paciente.setApellido("González");
        paciente.setDni("98765432");

        when(pacientesServicio.buscarUnPaciente(1L)).thenReturn(paciente);

        mockMvc.perform(get("/api/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("María"))
                .andExpect(jsonPath("$.apellido").value("González"))
                .andExpect(jsonPath("$.dni").value("98765432"));
    }

    @Test
    void testListarTodos() throws Exception {
        PacienteDTO paciente1 = new PacienteDTO();
        paciente1.setNombre("María");
        paciente1.setApellido("González");

        PacienteDTO paciente2 = new PacienteDTO();
        paciente2.setNombre("Juan");
        paciente2.setApellido("Pérez");

        Collection<PacienteDTO> listaPacientes = new ArrayList<>();
        listaPacientes.add(paciente1);
        listaPacientes.add(paciente2);

        when(pacientesServicio.buscarTodos()).thenReturn((Set<PacienteDTO>) listaPacientes);

        mockMvc.perform(get("/api/pacientes/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("María"))
                .andExpect(jsonPath("$[1].nombre").value("Juan"));
    }

    @PostMapping
    public ResponseEntity<?> agregarTurno(@RequestBody TurnoDTO turnoDTO) {
        turnosServicio.registrarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody TurnoDTO turnoDTO) {
        turnosServicio.modificarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        turnosServicio.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Turno Eliminado");
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarUno(@PathVariable Long id) {
        return turnosServicio.buscarUnTurno(id);
    }

    @GetMapping("/all")
    public Collection<TurnoDTO> listarTodos() {
        return turnosServicio.buscarTodos();
    }

}
