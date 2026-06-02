package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Solicitud;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:8080")
public class ControladorEmpleado {

    private final IEmpleadoRepository iEmpleadoRepository;

    public ControladorEmpleado(IEmpleadoRepository iEmpleadoRepository) {
        this.iEmpleadoRepository = iEmpleadoRepository;
    }

    @GetMapping
    public List<Empleado> empleados()
    {
        return iEmpleadoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> crearEmpleado(@RequestBody Empleado empleado)
    {
        iEmpleadoRepository.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitud creada con exito");
    }
    @PutMapping("/{id}")
    public String modificarEmpleado(@PathVariable Long legajo, @RequestBody Empleado empleado)
    {
        empleado.setLegajo(legajo);
        iEmpleadoRepository.save(empleado);
        return "Empleado modificado con exito";
    }
}
