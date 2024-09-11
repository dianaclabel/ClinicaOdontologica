package com.example.ClinicaOdontologica.repositorios;

import com.example.ClinicaOdontologica.entidades.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOdontologosRepositorio extends JpaRepository<Odontologo, Long> {

}
