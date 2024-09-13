package com.example.ClinicaOdontologica.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
public class PacienteDTO {
    private Long id;
    private String nombre, apellido, dni;
    private String fechaAlta;
    private DomicilioDTO domicilio;

    public PacienteDTO(String nombre, String apellido, String dni, String fechaAlta, DomicilioDTO domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta.substring(0, 10); // 2024-01-02
        this.domicilio = domicilio;
    }
    public PacienteDTO() {
    }

    // This ensures that the date is formatted correctly
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta.substring(0, 10);
    }
}
