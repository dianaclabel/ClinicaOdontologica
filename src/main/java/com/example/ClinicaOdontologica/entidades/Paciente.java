package com.example.ClinicaOdontologica.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "domicilio_id")
    private Domicilio domicilio;

    @Column(nullable = false)
    private String DNI;

    @Column(nullable = false, name = "fecha_alta")
    private Date fechaAlta;
}
