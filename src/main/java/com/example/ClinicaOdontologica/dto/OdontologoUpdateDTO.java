package com.example.ClinicaOdontologica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OdontologoUpdateDTO {

    private String nombre;

    private String apellido;

    private String matricula;
}
