package com.example.ClinicaOdontologica.servicios;

import com.example.ClinicaOdontologica.model.OdontologoDTO;

import java.util.Set;

public interface IOdontologosSevicio {
    OdontologoDTO registrarOdontologo(OdontologoDTO odontologoDTO) ;
    void eliminarOdontologo(Long id);
    OdontologoDTO modificarOdontologo(OdontologoDTO odontologoDTO);
    OdontologoDTO buscarUnOdontologo(Long id);
    Set<OdontologoDTO> buscarTodos();

/*
    public Odontologo registrar(OdontologoCreateDTO odontologo);

    public List<Odontologo> listar();

    public Odontologo buscarPorId(Long id);

    public void borrar(Long id);

    public Odontologo actualizar(Long id, OdontologoUpdateDTO odontologo);
*/


}
