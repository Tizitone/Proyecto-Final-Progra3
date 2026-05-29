package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.dto.RegistroDTO;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.service.RegistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class ControladorRegistro {

    private final RegistroService registroService;

    public ControladorRegistro(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public List<Registro> listarRegistros()
    {
        return registroService.listarRegistros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> buscarRegistro(@PathVariable Long id){
        return ResponseEntity.ok(registroService.buscarPorId(id));
    }

    @PostMapping
    public String crearRegistro(@RequestBody RegistroDTO dto)
    {
        ResponseEntity.status(HttpStatus.CREATED).body(registroService.crearRegistro(dto));
        return "Registro creado correctamente";
    }
    @PutMapping("/{id}")
    public String modificarRegistros(@PathVariable Long id, @RequestBody RegistroDTO dto)
    {
        ResponseEntity.ok(registroService.modificarRegistro(id, dto));
        return "Registro actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable Long id){
        registroService.eliminarRegistro(id);
        return ResponseEntity.ok("Registro eliminado correctamente");
    }
}
