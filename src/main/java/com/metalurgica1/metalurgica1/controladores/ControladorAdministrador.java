package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.dto.AdministradorDTO;
import com.metalurgica1.metalurgica1.dto.CrearAdministradorDTO;
import com.metalurgica1.metalurgica1.modelo.Administrador;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.IAdministradorRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.servicios.AdministradorService;
import com.metalurgica1.metalurgica1.servicios.Excepciones.AdministradorNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<AdministradorDTO> buscarAdmin(@PathVariable Long id)
    {

        try {
            AdministradorDTO a = administradorService.buscarAdministrador(id);
            return ResponseEntity.ok(a);
        } catch (AdministradorNoEncontradoException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping
    public ResponseEntity<CrearAdministradorDTO> crearAdministrador(@RequestBody CrearAdministradorDTO administrador)
    {
        administradorService.crearAdministrador(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> modificarAdministrador(@PathVariable Long id,@RequestBody AdministradorDTO dto)
    {
        try
        {
            administradorService.modificarAdministrador(id,dto);
            return ResponseEntity.ok(dto);
        } catch (AdministradorNoEncontradoException e) {
            e.printStackTrace();
            return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
        }
    }
}
