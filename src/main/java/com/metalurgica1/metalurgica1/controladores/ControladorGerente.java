package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Empleado_Gerente;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.IGerenteRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metalurgica1.metalurgica1.modelo.Registro;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gerentes")
@CrossOrigin(origins = "http://localhost:8080")
    public class ControladorGerente {

        private final IGerenteRepository iGerenteRepository;
        private final IRegistroRepository iRegistroRepository;
        private final ITareaRepository iTareaRepository;

        public ControladorGerente(IGerenteRepository iGerenteRepository, IRegistroRepository iRegistroRepository, ITareaRepository iTareaRepository) {
            this.iGerenteRepository = iGerenteRepository;
            this.iRegistroRepository = iRegistroRepository;
            this.iTareaRepository = iTareaRepository;
        }

        @GetMapping
        public List<Empleado_Gerente> listarGerentes() {
            return iGerenteRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Empleado_Gerente> buscarGerente(@PathVariable Long id) {
            Optional<Empleado_Gerente> gerente = iGerenteRepository.findById(id);
            if (gerente.isPresent()) {
                return ResponseEntity.ok(gerente.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        @PostMapping
        public ResponseEntity<String> crearGerente(@RequestBody Empleado_Gerente gerente) {
            iGerenteRepository.save(gerente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Gerente creado con exito");
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> modificarGerente(@PathVariable Long id, @RequestBody Empleado_Gerente gerente) {
            if (!iGerenteRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gerente no encontrado");
            }
            gerente.setLegajo(id);
            iGerenteRepository.save(gerente);
            return ResponseEntity.ok("Gerente modificado con exito");
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> eliminarGerente(@PathVariable Long id) {
            if (!iGerenteRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gerente no encontrado");
            }
            iGerenteRepository.deleteById(id);
            return ResponseEntity.ok("Gerente eliminado con exito");
        }


        @GetMapping("/registros")
        public List<Registro> verRegistros() {
            return iRegistroRepository.findAll();
        }

        @GetMapping("/tareas")
        public List<Tarea> verTareas() {
            return iTareaRepository.findAll();
        }
}

