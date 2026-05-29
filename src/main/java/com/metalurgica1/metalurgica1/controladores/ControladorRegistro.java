package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.RegistroDTO;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.servicio.RegistroServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class ControladorRegistro {

    private final RegistroServicio registroServicio;

    public ControladorRegistro(RegistroServicio registroServicio) {
        this.registroServicio = registroServicio;
    }

    @GetMapping
    public List<Registro> listarRegistros()
    {
        return registroServicio.listarRegistros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> buscarRegistro(@PathVariable Long id){
        return ResponseEntity.ok(registroServicio.buscarPorId(id));
    }

    @PostMapping
    public String crearRegistro(@RequestBody RegistroDTO dto)
    {
        ResponseEntity.status(HttpStatus.CREATED).body(registroServicio.crearRegistro(dto));
        return "Registro creado correctamente";
    }
    @PutMapping("/{id}")
    public String modificarRegistros(@PathVariable Long id, @RequestBody RegistroDTO dto)
    {
        ResponseEntity.ok(registroServicio.modificarRegistro(id, dto));
        return "Registro actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable Long id){
        registroServicio.eliminarRegistro(id);
        return ResponseEntity.ok("Registro eliminado correctamente");
    }
}
