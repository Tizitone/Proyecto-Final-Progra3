package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.EmpleadoModeloDTO;
import com.metalurgica1.metalurgica1.service.EmpleadoModeloService;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/empleadosGeneral")
@Validated
public class ControladorEmpleadosGeneral {

    @Autowired
    private EmpleadoModeloService empleadoModeloService;

    public ControladorEmpleadosGeneral(EmpleadoModeloService empleadoModeloService) {
        this.empleadoModeloService = empleadoModeloService;
    }

    @GetMapping("/buscar/all")
    public ResponseEntity<List<EmpleadoModeloDTO>> listarTodosLosEmpleados() {
        List<EmpleadoModeloDTO> empleadosGeneral = empleadoModeloService.listarTodosEmpleados();
        return ResponseEntity.ok(empleadosGeneral);
    }


    @GetMapping("/buscar/email")
    public ResponseEntity<Optional<EmpleadoModeloDTO>> buscarPorEmail(@RequestParam String email){
        return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorEmail(email));
    }

    @GetMapping("/buscar/legajo")
    public ResponseEntity<EmpleadoModeloDTO> buscarPorLegajo(@RequestParam Long legajo){
        return ResponseEntity.ok(empleadoModeloService.buscarEmpleadoPorId(legajo));
    }
}
