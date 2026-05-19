package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ControladorCliente {
    private final IClienteRepository iClienteRepository;

    public ControladorCliente(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    @GetMapping
    public List<Cliente> listarClientes()
    {
        return iClienteRepository.findAll();
    }
    @PostMapping
    public String crearCliente(@RequestBody Cliente cliente)
    {
        iClienteRepository.save(cliente);
        return "Cliente creado con exito";
    }
    @PutMapping("/{id}")
    public String modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente)
    {
        cliente.setIdCliente(id);
        iClienteRepository.save(cliente);
        return "Cliente modificado con exito";
    }
}
