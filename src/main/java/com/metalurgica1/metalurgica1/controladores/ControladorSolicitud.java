package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.SolicitudDTO;
import com.metalurgica1.metalurgica1.service.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class ControladorSolicitud {

    private final SolicitudService solicitudService;

    public ControladorSolicitud(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> listarTodasSolicitudes() {
        List<SolicitudDTO> solicitudes = solicitudService.listarTodasSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> listarSolicitud(@PathVariable Long id) {
        SolicitudDTO solicitud = solicitudService.listarSolicitud(id);
        return ResponseEntity.ok(solicitud);
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> crearSolicitud(@RequestBody SolicitudDTO solicitud) {
        SolicitudDTO response = solicitudService.crearSolicitud(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> modificarSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO solicitud){
        SolicitudDTO solicitudActualizada = solicitudService.modificarSolicitud(id, solicitud);
        return ResponseEntity.ok(solicitudActualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SolicitudDTO> eliminarSolicitud(@PathVariable Long id){
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
