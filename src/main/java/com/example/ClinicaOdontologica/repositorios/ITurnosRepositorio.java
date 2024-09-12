package com.example.ClinicaOdontologica.repositorios;


import com.example.ClinicaOdontologica.entidades.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnosRepositorio extends JpaRepository<Turno, Long> {
}
