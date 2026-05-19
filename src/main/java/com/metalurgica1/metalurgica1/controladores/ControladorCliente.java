package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ControladorCliente {

    private final IClienteRepository iClienteRepository;
    private final IRegistroRepository iRegistroRepository;

    public ControladorCliente(IClienteRepository iClienteRepository, IRegistroRepository iRegistroRepository) {
        this.iClienteRepository = iClienteRepository;
        this.iRegistroRepository = iRegistroRepository;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return iClienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = iClienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        iClienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (!iClienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        cliente.setId_cliente(id);
        iClienteRepository.save(cliente);
        return ResponseEntity.ok("Cliente modificado con exito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        if (!iClienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        iClienteRepository.deleteById(id);
        return ResponseEntity.ok("Cliente eliminado con exito");
    }

    //ver encargos de un cliente
    @GetMapping("/{id}/encargos")
    public ResponseEntity<List<Registro>> verEncargosDeCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = iClienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(cliente.get().getEncargos());
    }
}
