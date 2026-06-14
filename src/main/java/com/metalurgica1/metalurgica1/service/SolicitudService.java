package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.SolicitudDTO;
import com.metalurgica1.metalurgica1.modelo.Solicitud;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.ISolicitudRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.SolicitudNoEncontradaException;
import jakarta.transaction.Transactional;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SolicitudService {

    private final ISolicitudRepository iSolicitudRepository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    public SolicitudService(ISolicitudRepository iSolicitudRepository) {
        this.iSolicitudRepository = iSolicitudRepository;
    }

    public List<SolicitudDTO> listarTodasSolicitudes(){
        return iSolicitudRepository.findAll()
                .stream()
                .filter(p-> p.getEEstadoActividad() == (EEstadoActividad.ACTIVO))
                .map(this::convertirADTO)
                .toList();
    }

    public List<SolicitudDTO> buscarSolicitudPorDescripcion(String descripcion){

        if (descripcion == null || descripcion.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<Solicitud> s = iSolicitudRepository.findByDescripcionContainingIgnoreCase(descripcion);
        return s.stream().filter(p-> p.getEEstadoActividad()== EEstadoActividad.ACTIVO).map(p-> new SolicitudDTO(p.getId(), p.getNombre(),p.getEmail(),p.getTelefono(),p.getDescripcion())).collect(Collectors.toList());
    }

    public SolicitudDTO crearSolicitud(SolicitudDTO dto) {
        Solicitud s = new Solicitud();
        s.setNombre(dto.nombre());
        s.setEmail(dto.email());
        s.setTelefono(dto.telefono());
        s.setDescripcion(dto.descripcion());
        s.setEEstadoActividad(EEstadoActividad.ACTIVO);

        Set<ConstraintViolation<Solicitud>> violations = validator.validate(s);

        for(ConstraintViolation<Solicitud> violation : violations)
        {
            log.error(violation.getMessage());
        }
        if(!violations.isEmpty())
        {
            throw new RuntimeException();
        }

        Solicitud nuevaSolicitud = iSolicitudRepository.save(s);

        return convertirADTO(nuevaSolicitud);
    }

    public SolicitudDTO modificarSolicitud(Long id, SolicitudDTO dto) throws SolicitudNoEncontradaException {
        Solicitud s = iSolicitudRepository.findById(id).orElseThrow(()->new SolicitudNoEncontradaException("modificar solicitud"));
        s.setNombre(dto.nombre());
        s.setEmail(dto.email());
        s.setTelefono(dto.telefono());
        s.setDescripcion(dto.descripcion());

        Solicitud nuevaSolicitud = iSolicitudRepository.save(s);

        return convertirADTO(nuevaSolicitud);
    }

    public void eliminarSolicitud(Long id) throws SolicitudNoEncontradaException {
        Solicitud s = iSolicitudRepository.findById(id).orElseThrow(()->new SolicitudNoEncontradaException("eliminar solicitud"));

        s.setEEstadoActividad(EEstadoActividad.CANCELADO);

        iSolicitudRepository.save(s);
    }

    private SolicitudDTO convertirADTO(Solicitud solicitud){

        return new SolicitudDTO(
                solicitud.getId(),
                solicitud.getNombre(),
                solicitud.getEmail(),
                solicitud.getTelefono(),
                solicitud.getDescripcion());
    }

}
