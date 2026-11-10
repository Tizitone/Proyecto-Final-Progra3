package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.ClienteDTO;
import com.metalurgica1.metalurgica1.DTO.CrearClienteDTO;
import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.AccesoDenegadoException;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ClienteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final IClienteRepository iClienteRepository;

    public ClienteService(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    public List<ClienteDTO> listarTodosClientes() {
        return iClienteRepository.findAll()
                .stream()
                .filter(p-> p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(this::convertirADTOResponse)
                .toList();
    }

    public ClienteDTO buscarCliente(Long id) throws ClienteNoEncontradoException, AccesoDenegadoException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cliente = iClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado."));

        boolean esAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!esAdmin && !cliente.getEmail().equalsIgnoreCase(ObtenerCorreoLogeado())) {
            throw new AccesoDenegadoException("No tienes permisos para acceder a este perfil.");
        }
        return convertirADTOResponse(cliente);
    }
    
    public CrearClienteDTO crearCliente(CrearClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.email());
        cliente.setContrasenia(passwordEncoder.encode(dto.contrasenia()));
        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setDni(dto.dni());
        cliente.setEEstadoActividad(EEstadoActividad.ACTIVO);

        cliente = iClienteRepository.save(cliente);

        return convertirADTORequest(cliente);
    }

    public ClienteDTO modificarClienteSinContrasenia(Long id, ClienteDTO dto) throws ClienteNoEncontradoException {
        Cliente cliente = iClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado."));

        cliente.setEmail(dto.email());
        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setDni(dto.dni());

        Cliente clienteActualizado = iClienteRepository.save(cliente);

        return convertirADTOResponse(clienteActualizado);
    }
    public CrearClienteDTO modificarClienteConContrasenia(Long id, CrearClienteDTO dto) throws ClienteNoEncontradoException {
        Cliente cliente = iClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado."));

        cliente.setEmail(dto.email());
        cliente.setContrasenia(passwordEncoder.encode(dto.contrasenia()));
        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setDni(dto.dni());

        Cliente clienteActualizado = iClienteRepository.save(cliente);

        return convertirADTORequest(clienteActualizado);
    }

    public void eliminarCliente(Long id) throws ClienteNoEncontradoException {
        Cliente c = iClienteRepository.findById(id).orElseThrow(()-> new ClienteNoEncontradoException("no se pudo eliminar cliente"));
        c.setEEstadoActividad(EEstadoActividad.CANCELADO);
        iClienteRepository.save(c);
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
    private String ObtenerCorreoLogeado()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
