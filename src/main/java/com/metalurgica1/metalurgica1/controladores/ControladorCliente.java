package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.DTO.CrearClienteDTO;
import com.metalurgica1.metalurgica1.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.metalurgica1.metalurgica1.DTO.ClienteDTO;

import java.util.List;

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
    public ResponseEntity<ClienteDTO> listarCliente(@PathVariable Long id){
        ClienteDTO cliente = clienteService.listarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<CrearClienteDTO> crearCliente(@RequestBody CrearClienteDTO cliente){
        CrearClienteDTO response = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrearClienteDTO> modificarCliente(@PathVariable Long id, @RequestBody CrearClienteDTO cliente){
        CrearClienteDTO clienteActualizado = clienteService.modificarCliente(id, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
