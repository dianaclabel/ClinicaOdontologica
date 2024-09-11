package com.example.ClinicaOdontologica.servicios.impl;

import com.example.ClinicaOdontologica.dto.OdontologoCreateDTO;
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
    public Odontologo registrar(OdontologoCreateDTO odontologo) {
        return null;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologosRepositorio.findAll();
    }
}
