package com.example.ClinicaOdontologica.servicios;


import com.example.ClinicaOdontologica.model.PacienteDTO;

import java.util.Set;

public interface IPacientesServicio {
    PacienteDTO registrarPaciente(PacienteDTO pacienteDTO);
    void eliminarPaciente(Long id);
    PacienteDTO modificarPaciente(Long id,PacienteDTO pacienteDTO);
    PacienteDTO buscarUnPaciente(Long id);
    Set<PacienteDTO> buscarTodos();

    /*
    public Paciente registrar(PacienteCreateDTO paciente);

    public List<Paciente> listar();

    public Paciente buscarPorId(Long id);

    public void borrar(Long id);

    public Paciente actualizar(Long id, PacienteUpdateDTO paciente);
*/

}
