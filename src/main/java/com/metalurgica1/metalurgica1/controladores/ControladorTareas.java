package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class ControladorTareas {

    private final ITareaRepository ITareaRepository;


    public ControladorTareas(ITareaRepository ITareaRepository) {
        this.ITareaRepository = ITareaRepository;
    }

    @GetMapping
    public List<Tarea> listarTareas()
    {
        return ITareaRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(Tarea tarea)
    {
        ITareaRepository.save(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public String modificarTarea(@PathVariable Long id,@RequestBody Tarea tarea)
    {
        tarea.setId(id);
        ITareaRepository.save(tarea);
        return "Tarea actualizada correctamente";
    }
}
