package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.servicio.TareaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class ControladorTareas {

    private final TareaServicio tareaServicio;

    public ControladorTareas(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }


    @GetMapping
    public List<Tarea> listarTareas()
    {
        return tareaServicio.listarTareas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarTarea(@PathVariable Long id){
        return ResponseEntity.ok(tareaServicio.buscarPorId(id));
    }

    @PostMapping
    public String crearTarea(@RequestBody TareaDTO dto)
    {
        ResponseEntity.status(HttpStatus.CREATED).body(tareaServicio.crearTarea(dto));
        return "Tarea creada correctamente";
    }

    @PutMapping("/{id}")
    public String modificarTarea(@PathVariable Long id,@RequestBody TareaDTO dto)
    {
        ResponseEntity.ok(tareaServicio.modificarTarea(id, dto));
        return "Tarea actualizada correctamente";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Long id){
        tareaServicio.eliminarTarea(id);
        return ResponseEntity.ok("Tarea eliminada correctamente");
    }
}
