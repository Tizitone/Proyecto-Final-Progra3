package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.SolicitudDTO;
import com.metalurgica1.metalurgica1.modelo.Solicitud;
import com.metalurgica1.metalurgica1.repositorio.ISolicitudRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.SolicitudNoEncontradaException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SolicitudDTO listarSolicitud(Long id){
        Solicitud solicitud = iSolicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada."));

                return convertirADTO(solicitud);
    }

    public SolicitudDTO crearSolicitud(SolicitudDTO dto){
        Solicitud solicitud = new Solicitud();
        solicitud.setNombre(dto.nombre());
        solicitud.setEmail(dto.email());
        solicitud.setTelefono(dto.telefono());
        solicitud.setDescripcion(dto.descripcion());

        solicitud = iSolicitudRepository.save(solicitud);

        return convertirADTO(solicitud);
    }

    public SolicitudDTO modificarSolicitud(Long id, SolicitudDTO dto){
        Solicitud solicitud = iSolicitudRepository.findById(id)
                .orElseThrow(() -> new SolicitudNoEncontradaException("Solicitud no encontrada."));

        solicitud.setNombre(dto.nombre());
        solicitud.setEmail(dto.email());
        solicitud.setTelefono(dto.telefono());
        solicitud.setDescripcion(dto.descripcion());

        Solicitud solicitudActualizada = iSolicitudRepository.save(solicitud);

        return convertirADTO(solicitudActualizada);
    }

    public void eliminarSolicitud(Long id){
        if(!iSolicitudRepository.existsById(id))
            throw new SolicitudNoEncontradaException("Solicitud no encontrada.");

        iSolicitudRepository.deleteById(id);
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
