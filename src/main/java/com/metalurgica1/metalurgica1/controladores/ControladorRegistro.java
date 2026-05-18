package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class ControladorRegistro {

    private final IRegistroRepository iRegistroRepository;

    public ControladorRegistro(IRegistroRepository iRegistroRepository) {
        this.iRegistroRepository = iRegistroRepository;
    }

    @GetMapping
    public List<Registro> listarRegistros()
    {
        return iRegistroRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<String> crearRegistro(@RequestBody Registro registro)
    {
        iRegistroRepository.save(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitud creada con exito");
    }
    @PutMapping("/{id}")
    public String modificarRegistros(@PathVariable Long id, @RequestBody Registro registro)
    {
        registro.setId(id);
        iRegistroRepository.save(registro);
        return "Empleado modificado con exito";
    }
}
