package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.AdministradorDTO;
import com.metalurgica1.metalurgica1.DTO.CrearAdministradorDTO;
import com.metalurgica1.metalurgica1.service.AdministradorService;
import com.metalurgica1.metalurgica1.service.Excepciones.AdministradorNoEncontradoException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class ControladorAdministrador {

    @Autowired
    private AdministradorService administradorService;

    public ControladorAdministrador(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public List<AdministradorDTO> listarAdministradores()
    {
        return administradorService.listarAdministradores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> buscarAdmin(@PathVariable Long id) {

        try {
            AdministradorDTO a = administradorService.buscarAdministrador(id);
            return ResponseEntity.ok(a);
        } catch (AdministradorNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping
    public ResponseEntity<CrearAdministradorDTO> crearAdministrador(@Valid @RequestBody CrearAdministradorDTO administrador) {
        CrearAdministradorDTO administradorDTO = administradorService.crearAdministrador(administrador);
        return new ResponseEntity<>(administradorDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrearAdministradorDTO> modificarAdministrador(@Valid @PathVariable Long id,@RequestBody CrearAdministradorDTO dto) {
        try
        {
            CrearAdministradorDTO administradorDTO = administradorService.modificarAdministrador(id,dto);
            return ResponseEntity.ok(administradorDTO);
        } catch (AdministradorNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }
}
