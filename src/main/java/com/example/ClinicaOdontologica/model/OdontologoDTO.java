package com.example.ClinicaOdontologica.model;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
public class OdontologoDTO {
    private Long id;

    private String nombre;


    private String apellido;

    private String matricula;
}
