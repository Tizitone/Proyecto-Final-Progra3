package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.ClienteDTO;
import com.metalurgica1.metalurgica1.DTO.CrearClienteDTO;
import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ClienteService {

    private final IClienteRepository iClienteRepository;

    public ClienteService(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    public List<ClienteDTO> listarTodosClientes() {
        return iClienteRepository.findAll()
                .stream()
                .map(this::convertirADTOResponse)
                .toList();
    }

    public ClienteDTO listarCliente(Long id){
        Cliente cliente = iClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado."));

        return convertirADTOResponse(cliente);
    }

    public CrearClienteDTO crearCliente(CrearClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.email());
        cliente.setContrasenia(dto.contrasenia());
        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setDni(dto.dni());
        cliente.setEtiquetaDeAcceso(EEtiquetaDeAcceso.CLIENTE);

        cliente = iClienteRepository.save(cliente);

        return convertirADTORequest(cliente);
    }

    public CrearClienteDTO modificarCliente(Long id, CrearClienteDTO dto) {
        Cliente cliente = iClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado."));

        cliente.setEmail(dto.email());
        cliente.setContrasenia(dto.contrasenia());
        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setDni(dto.dni());

        Cliente clienteActualizado = iClienteRepository.save(cliente);

        return convertirADTORequest(clienteActualizado);
    }

    public void eliminarCliente(Long id){
        if(!iClienteRepository.existsById(id))
            throw new ClienteNoEncontradoException("Cliente no encontrado.");

        iClienteRepository.deleteById(id);
    }

    private ClienteDTO convertirADTOResponse(Cliente cliente) {
        return new ClienteDTO(
                cliente.getEmail(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getDni(),
                cliente.getIdCliente()
        );
    }

    private CrearClienteDTO convertirADTORequest(Cliente cliente) {
        return new CrearClienteDTO(
                cliente.getEmail(),
                cliente.getContrasenia(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getDni()
        );
    }

}
