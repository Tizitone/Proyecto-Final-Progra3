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

    @GetMapping
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


    @GetMapping("/buscar/email")
    public ResponseEntity<GerenteDTO> buscarPorEmail(@RequestParam String email){
        try {
            return ResponseEntity.ok(gerenteService.buscarGerentePorMail(email));
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<GerenteDTO>> buscarPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(gerenteService.buscarGerentePorNombre(nombre));
    }

    @GetMapping("/buscar/telefono")
    public ResponseEntity<GerenteDTO> buscarPorTelefono(@RequestParam String telefono){
        try{
            return ResponseEntity.ok(gerenteService.buscarGerentePorTelefono(telefono));
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/dni")
    public ResponseEntity<GerenteDTO> buscarPorDni(@RequestParam Long dni){
        try{
            return ResponseEntity.ok(gerenteService.buscarGerentePorDni(dni));
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
    public ResponseEntity<CrearGerenteDTO> modificarGerente(@Valid @PathVariable Long id, @RequestBody CrearGerenteDTO gerente) {
        try {
            CrearGerenteDTO gerenteActualizado = gerenteService.modificarGerente(id, gerente);
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

