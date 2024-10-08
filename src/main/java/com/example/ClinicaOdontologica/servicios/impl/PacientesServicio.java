package com.example.ClinicaOdontologica.servicios.impl;

import com.example.ClinicaOdontologica.config.SpringConfig;
import com.example.ClinicaOdontologica.entidades.Paciente;
import com.example.ClinicaOdontologica.model.PacienteDTO;
import com.example.ClinicaOdontologica.repositorios.IPacientesRepositorio;
import com.example.ClinicaOdontologica.servicios.IPacientesServicio;

import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PacientesServicio implements IPacientesServicio {

    private static final Logger logger = Logger.getLogger(PacientesServicio.class);
    @Autowired
    IPacientesRepositorio pacientesRepositorio;
    @Autowired
    SpringConfig springConfig;
    @Override
    public PacienteDTO registrarPaciente(PacienteDTO pacienteDTO) {
        logger.info("Iniciando registro de Paciente");
        Paciente paciente = springConfig.getModelMapper().map(pacienteDTO, Paciente.class);
        logger.info("Paciente guardado id: " + paciente.getId());
        return springConfig.getModelMapper().map(pacientesRepositorio.save(paciente), PacienteDTO.class);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacientesRepositorio.deleteById(id);
        logger.info("Se eliminó el paciente con id: " + id);
    }

    @Override
    public PacienteDTO modificarPaciente(Long id,PacienteDTO pacienteDTO) {
        logger.info("Modificando paciente id: " + id);
        PacienteDTO pacienteModificado = null;
        Optional<Paciente> pacienteAModificar = pacientesRepositorio.findById(id);
        if (pacienteAModificar.isPresent()) {
            Paciente paciente = springConfig.getModelMapper().map(pacienteDTO, Paciente.class);
            paciente.setId(id); // Ensure the ID is set correctly
            pacienteModificado = springConfig.getModelMapper().map(pacientesRepositorio.save(paciente), PacienteDTO.class);
        }
        logger.info("Paciente modificado");
        return pacienteModificado;
    }

    @Override
    public PacienteDTO buscarUnPaciente(Long id) {
        logger.info("Buscando paciente id: " + id);
        Optional<Paciente> paciente = pacientesRepositorio.findById(id);
        PacienteDTO pacienteDTO = null;
        if (paciente.isPresent()){
            pacienteDTO =  springConfig.getModelMapper().map(paciente, PacienteDTO.class);
        }
        logger.info("Paciente encontrado por Id");
        return pacienteDTO;
    }

    @Override
    public Set<PacienteDTO> buscarTodos() {
        logger.info("Buscando todos los pacientes");
        List<Paciente> pacientes = pacientesRepositorio.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for (Paciente paciente: pacientes){
            pacientesDTO.add( springConfig.getModelMapper().map(paciente, PacienteDTO.class));
        }
        logger.info("Se encontraron todos los Pacientes");
        return pacientesDTO;

    }
}
