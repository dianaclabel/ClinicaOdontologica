package com.example.ClinicaOdontologica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OdontologoCreateDTO{

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank (message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank (message = "La matricula es obligatoria")
    private String matricula;

}

