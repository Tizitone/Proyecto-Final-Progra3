package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Solicitud;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metalurgica1.metalurgica1.repositorio.ISolicitudRepository;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class ControladorSolicitud {

    private final ISolicitudRepository ISolicitudRepository;

    public ControladorSolicitud(ISolicitudRepository ISolicitudRepository) {
        this.ISolicitudRepository = ISolicitudRepository;
    }

    @GetMapping
    public List<Solicitud> listarSolicitudes()
    {
        return ISolicitudRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> crearSolicitud(@RequestBody Solicitud solicitud)
    {
        ISolicitudRepository.save(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitud creada con exito");
    }
}
