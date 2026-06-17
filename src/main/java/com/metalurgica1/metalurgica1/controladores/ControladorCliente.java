package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.CrearClienteDTO;
import com.metalurgica1.metalurgica1.service.ClienteService;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metalurgica1.metalurgica1.DTO.ClienteDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")

public class ControladorCliente {

    private final ClienteService clienteService;

    public ControladorCliente(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes(){
        List<ClienteDTO> clientes = clienteService.listarTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long id){
        try {
            ClienteDTO cliente = clienteService.buscarCliente(id);
            return ResponseEntity.ok(cliente);
        } catch (ClienteNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<ClienteDTO> buscarPorEmail(@RequestParam String email){
        return ResponseEntity.ok(clienteService.buscarClientePorEmail(email));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ClienteDTO>> buscarPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(clienteService.buscarClientePorNombre(nombre));
    }

    @GetMapping("/buscar/telefono")
    public ResponseEntity<ClienteDTO> buscarPorTelefono(@RequestParam String telefono){
        return ResponseEntity.ok(clienteService.buscarClientePorTelefono(telefono));
    }

    @GetMapping("/buscar/dni")
    public ResponseEntity<ClienteDTO> buscarPorDni(@RequestParam Long dni){
        return ResponseEntity.ok(clienteService.buscarClientePorDni(dni));
    }

    @PostMapping
    public ResponseEntity<CrearClienteDTO> crearCliente(@Valid @RequestBody CrearClienteDTO cliente){
        CrearClienteDTO response = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrearClienteDTO> modificarCliente(@Valid @PathVariable Long id, @RequestBody CrearClienteDTO cliente){
        try {
            CrearClienteDTO clienteActualizado = clienteService.modificarCliente(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (ClienteNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> eliminarCliente(@PathVariable Long id){
        try
        {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNoEncontradoException e) {
            log.error("",e);
            return ResponseEntity.notFound().build();
        }
    }
}
