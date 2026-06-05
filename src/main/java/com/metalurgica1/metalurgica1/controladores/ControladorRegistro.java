package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.RegistroDTO;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.RegistroNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import com.metalurgica1.metalurgica1.service.RegistroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class ControladorRegistro {

    private final RegistroService registroService;

    public ControladorRegistro(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public List<RegistroDTO> listarRegistros()
    {
        return registroService.listarRegistros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDTO> buscarRegistro(@PathVariable Long id){
        try {
            RegistroDTO registro = registroService.buscarRegistroPorId(id);
            return ResponseEntity.ok(registro);
        } catch (RegistroNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<RegistroDTO>> buscarPorTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(registroService.buscarRegistroPorTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<RegistroDTO> crearRegistro(@RequestBody RegistroDTO dto) {
        try {
            RegistroDTO registro = registroService.crearRegistro(dto);
            return ResponseEntity.ok(registro);
        } catch (TareaNoEncontradaExeption | ClienteNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDTO> modificarRegistros(@PathVariable Long id, @RequestBody RegistroDTO dto) {
        try {
            RegistroDTO registroModificado = registroService.modificarRegistro(id, dto);
            return ResponseEntity.ok(registroModificado);
        } catch (TareaNoEncontradaExeption | ClienteNoEncontradoException | RegistroNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable Long id){
        registroService.eliminarRegistro(id);
        return ResponseEntity.ok("Registro eliminado correctamente");
    }
}
