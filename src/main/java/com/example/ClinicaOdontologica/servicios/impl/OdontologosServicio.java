package com.example.ClinicaOdontologica.servicios.impl;

import com.example.ClinicaOdontologica.dto.OdontologoCreateDTO;
import com.example.ClinicaOdontologica.dto.OdontologoUpdateDTO;
import com.example.ClinicaOdontologica.entidades.Odontologo;
import com.example.ClinicaOdontologica.repositorios.IOdontologosRepositorio;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologosServicio implements IOdontologosSevicio {
    @Autowired
    private IOdontologosRepositorio odontologosRepositorio;

    @Override
    public Odontologo registrar(OdontologoCreateDTO odontologoDTO) {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre(odontologoDTO.getNombre());
        odontologo.setApellido(odontologoDTO.getApellido());
        odontologo.setMatricula(odontologoDTO.getMatricula());
        return odontologosRepositorio.save(odontologo);
    }

    @Override
    public List<Odontologo> listar() {
        return odontologosRepositorio.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologosRepositorio.findById(id).orElse(null);
    }

    @Override
    public void borrar(Long id) {
        odontologosRepositorio.deleteById(id);
    }

    @Override
    public Odontologo actualizar(Long id, OdontologoUpdateDTO odontologoDTO) {
        Odontologo odontologo = odontologosRepositorio.findById(id).orElse(null);
        if (odontologo != null) {

            if(odontologoDTO.getNombre() != null) odontologo.setNombre(odontologoDTO.getNombre());
            if (odontologoDTO.getApellido() != null) odontologo.setApellido(odontologoDTO.getApellido());
            if (odontologoDTO.getMatricula() != null) odontologo.setMatricula(odontologoDTO.getMatricula());

            return odontologosRepositorio.save(odontologo);
        }
        return null;
    }




}
