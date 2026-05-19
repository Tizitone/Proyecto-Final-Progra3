package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Administrador;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.IAdministradorRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class ControladorAdministrador {

    private final IAdministradorRepository iAdministradorRepository;


    public ControladorAdministrador(IAdministradorRepository iAdministradorRepository) {
        this.iAdministradorRepository = iAdministradorRepository;
    }

    @GetMapping
    public List<Administrador> listarAdministradores()
    {
        return iAdministradorRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Tarea> crearAdministrador(@RequestBody Administrador administrador)
    {
        iAdministradorRepository.save(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public String crearAdministrador(@PathVariable Long id,@RequestBody Administrador administrador)
    {
        administrador.setId_administrador(id);
        iAdministradorRepository.save(administrador);
        return "Tarea actualizada correctamente";
    }
}
