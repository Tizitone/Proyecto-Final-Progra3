package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.EmpleadoModeloDTO;
import com.metalurgica1.metalurgica1.service.EmpleadoModeloService;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/empleadosGeneral")
@CrossOrigin(origins = ("*"))
public class ControladorEmpleadosGeneral {

    @Autowired
    private EmpleadoModeloService empleadoModeloService;

    public ControladorEmpleadosGeneral(EmpleadoModeloService empleadoModeloService) {
        this.empleadoModeloService = empleadoModeloService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoModeloDTO>> listarTodosLosEmpleados() {
        List<EmpleadoModeloDTO> empleadosGeneral = empleadoModeloService.listarTodosEmpleados();
        return ResponseEntity.ok(empleadosGeneral);
    }


    @GetMapping("/buscar/email")
    public ResponseEntity<Optional<EmpleadoModeloDTO>> buscarPorEmail(@RequestParam String email){
        return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorEmail(email));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<Optional<EmpleadoModeloDTO>> buscarPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorNombre(nombre));
    }

    @GetMapping("/buscar/telefono")
    public ResponseEntity<Optional<EmpleadoModeloDTO>> buscarPorTelefono(@RequestParam String telefono){
        return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorTelefono(telefono));
    }

    @GetMapping("/buscar/dni")
    public ResponseEntity<Optional<EmpleadoModeloDTO>> buscarPorDni(@RequestParam Long dni){
        return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorDni(dni));
    }

    @GetMapping("/buscar/legajo")
    public ResponseEntity<Optional<EmpleadoModeloDTO>> buscarPorLegajo(@RequestParam Long legajo){
        try {
            return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorId(legajo));
        } catch (EmpleadoNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }

    }
}
