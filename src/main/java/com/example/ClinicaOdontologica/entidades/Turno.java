package com.example.ClinicaOdontologica.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
@Data
public class Turno {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fecha_hora", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha_hora;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;
}
