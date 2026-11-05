package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.CrearGerenteDTO;
import com.metalurgica1.metalurgica1.DTO.GerenteDTO;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import com.metalurgica1.metalurgica1.service.GerenteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/gerentes")
    public class ControladorGerente {
    private final GerenteService gerenteService;

    public ControladorGerente(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    @GetMapping("/buscar/all")
    public ResponseEntity<List<GerenteDTO>> listarTodosGerentes() {
        List<GerenteDTO> gerentes = gerenteService.listarTodosGerentes();
        return ResponseEntity.ok(gerentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GerenteDTO> buscarEmpleado(Long id) {
        try {
            GerenteDTO gerente = gerenteService.listarGerente(id);
            return ResponseEntity.ok(gerente);
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/legajo")
    public ResponseEntity<GerenteDTO> buscarPorLegajo(@RequestParam Long legajo){
        try{
            return ResponseEntity.ok(gerenteService.buscarGerentePorLegajo(legajo));
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CrearGerenteDTO> crearGerente(@Valid @RequestBody CrearGerenteDTO gerente) {
        CrearGerenteDTO response = gerenteService.crearGerente(gerente);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GerenteDTO> modificarGerenteSinPassword(@Valid @PathVariable Long id, @RequestBody GerenteDTO gerente) {
        try {
            GerenteDTO gerenteActualizado = gerenteService.modificarGerenteSinContrasenia(id, gerente);
            return ResponseEntity.ok(gerenteActualizado);
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/modificarPassword/{id}")
    public ResponseEntity<CrearGerenteDTO> modificarGerenteConPassword(@Valid @PathVariable Long id, @RequestBody CrearGerenteDTO gerente) {
        try {
            CrearGerenteDTO gerenteActualizado = gerenteService.modificarGerenteConContrasenia(id, gerente);
            return ResponseEntity.ok(gerenteActualizado);
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GerenteDTO> eliminarEmpleado(@PathVariable Long id){
        try {
            gerenteService.eliminarGerente(id);
            return ResponseEntity.noContent().build();
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }
}

