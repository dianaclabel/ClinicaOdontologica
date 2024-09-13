package com.example.ClinicaOdontologica.servicios.impl;

import com.example.ClinicaOdontologica.config.SpringConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.example.ClinicaOdontologica.entidades.Odontologo;
import com.example.ClinicaOdontologica.model.OdontologoDTO;
import com.example.ClinicaOdontologica.repositorios.IOdontologosRepositorio;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;

@Service
public class OdontologosServicio  implements IOdontologosSevicio{
    private static final Logger logger = Logger.getLogger(OdontologosServicio.class);
    @Autowired
    SpringConfig springConfig;
    @Autowired
    private IOdontologosRepositorio odontologosRepositorio;

    @Override
    public OdontologoDTO registrarOdontologo(OdontologoDTO odontologoDTO) {
        logger.info("Iniciando registro de Odontologo");
        Odontologo odontologo = springConfig.getModelMapper().map(odontologoDTO, Odontologo.class);
        logger.info("Odontologo Guardado");
        return springConfig.getModelMapper().map(odontologosRepositorio.save(odontologo), OdontologoDTO.class);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologosRepositorio.deleteById(id);
        logger.info("Se eliminó el odontologo con id: " + id);
    }

    @Override
    public OdontologoDTO modificarOdontologo( Long id, OdontologoDTO odontologoDTO) {
        logger.info("Modificando odontologo id: " + odontologoDTO.getId());

        Odontologo odontologoAModificar = odontologosRepositorio.findById(id).orElse(null);
        if (odontologoAModificar != null) {

            if(odontologoDTO.getNombre() != null) odontologoAModificar.setNombre(odontologoDTO.getNombre());
            if (odontologoDTO.getApellido() != null) odontologoAModificar.setApellido(odontologoDTO.getApellido());
            if (odontologoDTO.getMatricula() != null) odontologoAModificar.setMatricula(odontologoDTO.getMatricula());

            Odontologo odontologoGuardado = odontologosRepositorio.save(odontologoAModificar);
            logger.info("Odontologo Modificado");
            return springConfig.getModelMapper().map(odontologoGuardado, OdontologoDTO.class);
        }else{
            logger.info("Odontologo no encontrado");
            return null;
        }

    }

    @Override
    public OdontologoDTO buscarUnOdontologo(Long id) {
        logger.info("Buscando odontologo id: " + id);
        Optional<Odontologo> odontologo = odontologosRepositorio.findById(id);
        OdontologoDTO odontologoDTO = null;
        if (odontologo.isPresent()){
            odontologoDTO = springConfig.getModelMapper().map(odontologo, OdontologoDTO.class);
        }
        logger.info("Odontologo encontrado por Id");
        return odontologoDTO;
    }

    @Override
    public Set<OdontologoDTO> buscarTodos() {
        logger.info("Buscando todos los odontologos");
        List<Odontologo> odontologos = odontologosRepositorio.findAll();
        Set<OdontologoDTO> odontologoDTOS = new HashSet<>();

        for (Odontologo odontologo: odontologos){
            odontologoDTOS.add(springConfig.getModelMapper().map(odontologo, OdontologoDTO.class));
        }
        logger.info("Se encontraron todos los Odontologos");
        return odontologoDTOS;
    }
    }


    /*
    @Autowired
    private IOdontologosRepositorio odontologosRepositorio;

    @Override
    public Odontologo registrar(OdontologoCreateDTO odontologoDTO) {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre(odontologoDTO.getNombre());
        odontologo.setApellido(odontologoDTO.getApellido());
        odontologo.setMatricula(odontologoDTO.getMatricula());
        return odontologosRepositorio.save(odontologo);
    }

    @Override
    public List<Odontologo> listar() {
        return odontologosRepositorio.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologosRepositorio.findById(id).orElse(null);
    }

    @Override
    public void borrar(Long id) {
        odontologosRepositorio.deleteById(id);
    }

    @Override
    public Odontologo actualizar(Long id, OdontologoUpdateDTO odontologoDTO) {
        Odontologo odontologo = odontologosRepositorio.findById(id).orElse(null);
        if (odontologo != null) {

            if(odontologoDTO.getNombre() != null) odontologo.setNombre(odontologoDTO.getNombre());
            if (odontologoDTO.getApellido() != null) odontologo.setApellido(odontologoDTO.getApellido());
            if (odontologoDTO.getMatricula() != null) odontologo.setMatricula(odontologoDTO.getMatricula());

            return odontologosRepositorio.save(odontologo);
        }
        return null;
    }


*/


