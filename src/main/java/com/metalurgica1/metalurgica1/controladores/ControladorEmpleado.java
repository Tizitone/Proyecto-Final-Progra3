package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.service.EmpleadoService;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metalurgica1.metalurgica1.DTO.EmpleadoDTO;
import com.metalurgica1.metalurgica1.DTO.CrearEmpleadoDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/empleados")
public class ControladorEmpleado {

    private final EmpleadoService empleadoService;

    public ControladorEmpleado(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listarTodosEmpleados()
    {
        List<EmpleadoDTO> empleados = empleadoService.listarTodosEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> buscarEmpleado(Long id)
    {
        try {
            EmpleadoDTO empleado = empleadoService.listarEmpleado(id);
            return ResponseEntity.ok(empleado);
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<CrearEmpleadoDTO> crearEmpleado(@RequestBody CrearEmpleadoDTO empleado)
    {
        CrearEmpleadoDTO response = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrearEmpleadoDTO> modificarEmpleado(@PathVariable Long id, @RequestBody CrearEmpleadoDTO empleado)
    {
        try {
            CrearEmpleadoDTO empleadoActualizado = empleadoService.modificarEmpleado(id, empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CrearEmpleadoDTO> eliminarEmpleado(@PathVariable Long id){
        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.noContent().build();
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }


    }
}
