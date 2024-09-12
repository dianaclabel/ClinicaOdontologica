package com.example.ClinicaOdontologica.servicios;

import com.example.ClinicaOdontologica.model.TurnoDTO;

import java.util.Set;

public interface ITurnosServicio {
    TurnoDTO registrarTurno(TurnoDTO turnoDTO);
    void eliminarTurno(Long id);
    TurnoDTO modificarTurno(TurnoDTO turnoDTO);
    TurnoDTO buscarUnTurno(Long id);
    Set<TurnoDTO> buscarTodos();
}
