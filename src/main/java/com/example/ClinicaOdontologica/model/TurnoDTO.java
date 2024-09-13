package com.example.ClinicaOdontologica.model;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TurnoDTO {
    private Long id;
    private LocalDateTime fecha_hora;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
}

