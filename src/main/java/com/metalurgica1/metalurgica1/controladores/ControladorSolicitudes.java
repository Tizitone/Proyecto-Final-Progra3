package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metalurgica1.metalurgica1.repositorio.solicitudRepository;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class ControladorSolicitudes {

    @Autowired
    private solicitudRepository solicitudRepository;

    @GetMapping
    public List<Solicitud> listarSolicitudes()
    {
        return solicitudRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> crearSolicitud(@RequestBody Solicitud solicitud)
    {
        solicitudRepository.save(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitud creada con exito");
    }
}
