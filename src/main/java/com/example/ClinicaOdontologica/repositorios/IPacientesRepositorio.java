package com.example.ClinicaOdontologica.repositorios;

import com.example.ClinicaOdontologica.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPacientesRepositorio extends JpaRepository<Paciente, Long> {

}
