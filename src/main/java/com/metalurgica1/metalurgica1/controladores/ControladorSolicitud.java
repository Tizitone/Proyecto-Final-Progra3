package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.SolicitudDTO;
import com.metalurgica1.metalurgica1.service.Excepciones.SolicitudNoEncontradaException;
import com.metalurgica1.metalurgica1.service.SolicitudService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class ControladorSolicitud {

    private final SolicitudService solicitudService;

    public ControladorSolicitud(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping("/buscar/all")
    public ResponseEntity<List<SolicitudDTO>> listarTodasSolicitudes() {
        List<SolicitudDTO> solicitudes = solicitudService.listarTodasSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<SolicitudDTO>> listarSolicitud(@RequestParam(name = "desc", required = false) String desc) {
        if (desc == null || desc.trim().isEmpty() ) {
            return ResponseEntity.ok(new ArrayList<>());
        }
            List<SolicitudDTO> solicitud = solicitudService.buscarSolicitudPorDescripcion(desc);
            return ResponseEntity.ok(solicitud);
    }

    @PostMapping("/crear")
    public ResponseEntity<SolicitudDTO> crearSolicitud(@Valid @RequestBody SolicitudDTO solicitud) {
        SolicitudDTO response = solicitudService.crearSolicitud(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> modificarSolicitud(@Valid @PathVariable Long id, @RequestBody SolicitudDTO solicitud){
        try {
            SolicitudDTO solicitudActualizada = solicitudService.modificarSolicitud(id, solicitud);
            return ResponseEntity.ok(solicitudActualizada);
        } catch (SolicitudNoEncontradaException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<SolicitudDTO> eliminarSolicitud(@PathVariable Long id){
        try
        {
           solicitudService.eliminarSolicitud(id);
            return ResponseEntity.noContent().build();
        } catch (SolicitudNoEncontradaException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }
}
