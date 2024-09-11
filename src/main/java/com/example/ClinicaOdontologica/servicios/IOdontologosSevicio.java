package com.example.ClinicaOdontologica.servicios;

import com.example.ClinicaOdontologica.dto.OdontologoCreateDTO;
import com.example.ClinicaOdontologica.entidades.Odontologo;

import java.util.List;

public interface IOdontologosSevicio {

    public Odontologo registrar(OdontologoCreateDTO odontologo);

    public List<Odontologo> listar();

}
