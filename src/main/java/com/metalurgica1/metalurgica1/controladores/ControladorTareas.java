package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.CrearTareaDTO;
import com.metalurgica1.metalurgica1.DTO.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import com.metalurgica1.metalurgica1.service.TareaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class ControladorTareas {

    private final TareaService tareaService;

    public ControladorTareas(TareaService tareaService) {
        this.tareaService = tareaService;
    }


    @GetMapping
    public List<TareaDTO> listarTareas()
    {
        return tareaService.listarTareas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> buscarTarea(@PathVariable Long id){
        try
        {
            TareaDTO t = tareaService.buscarPorId(id);
            return ResponseEntity.ok(t);
        } catch (TareaNoEncontradaExeption e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<Tarea>> buscarTareas(@RequestParam String texto){
        return ResponseEntity.ok(tareaService.buscarPorTexto(texto));
    }

    @GetMapping("/buscar/{categoria}")
    public ResponseEntity<List<Tarea>> buscarPorCategoria(@RequestParam ECategorias categoria){
        return ResponseEntity.ok(tareaService.buscarPorCategoria(categoria));
    }

    @GetMapping("/buscar/fecha")
    public ResponseEntity<List<Tarea>> buscarPorFecha(@RequestParam @DateTimeFormat
            (iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
        return ResponseEntity.ok(tareaService.buscarPorFecha(fecha));
    }

    @PostMapping
    public ResponseEntity<CrearTareaDTO> crearTarea(@RequestBody CrearTareaDTO dto)
    {
        CrearTareaDTO c = tareaService.crearTarea(dto);
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrearTareaDTO> modificarTarea(@PathVariable Long id,@RequestBody CrearTareaDTO dto)
    {
        try {
            CrearTareaDTO t = tareaService.modificarTarea(id, dto);
            return ResponseEntity.ok(t);
        } catch (TareaNoEncontradaExeption e) {
            log.error("",e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Long id){
        tareaService.eliminarTarea(id);
        return ResponseEntity.ok("Tarea eliminada correctamente");
    }
}
