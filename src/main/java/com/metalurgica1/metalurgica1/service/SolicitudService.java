package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.SolicitudDTO;
import com.metalurgica1.metalurgica1.modelo.Solicitud;
import com.metalurgica1.metalurgica1.repositorio.ISolicitudRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.SolicitudNoEncontradaException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class SolicitudService {

    private final ISolicitudRepository iSolicitudRepository;

    public SolicitudService(ISolicitudRepository iSolicitudRepository) {
        this.iSolicitudRepository = iSolicitudRepository;
    }

    public List<SolicitudDTO> listarTodasSolicitudes(){
        return iSolicitudRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<SolicitudDTO> buscarSolicitudPorDescripcion(String descripcion){

        if (descripcion == null || descripcion.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String textoBusqueda = descripcion.trim();
        List<Solicitud> s = iSolicitudRepository.findByDescripcionPorPalabras(textoBusqueda);
        return s.stream().map(p-> new SolicitudDTO(p.getId(), p.getNombre(),p.getEmail(),p.getTelefono(),p.getDescripcion())).collect(Collectors.toList());
    }

    public SolicitudDTO crearSolicitud(SolicitudDTO dto)
    {
        Solicitud s = new Solicitud();
        s.setNombre(dto.nombre());
        s.setEmail(dto.email());
        s.setTelefono(dto.telefono());
        s.setDescripcion(dto.descripcion());

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

        iSolicitudRepository.delete(s);
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
