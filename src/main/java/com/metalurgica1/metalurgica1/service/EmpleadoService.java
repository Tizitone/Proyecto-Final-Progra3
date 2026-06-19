package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.EmpleadoDTO;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.DTO.CrearEmpleadoDTO;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmpleadoService {

    private final IEmpleadoRepository iEmpleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmpleadoService(IEmpleadoRepository iEmpleadoRepository) {
        this.iEmpleadoRepository = iEmpleadoRepository;
    }

    public List<EmpleadoDTO> listarTodosEmpleados() {
        return iEmpleadoRepository.findAll()
                .stream()
                .filter(p-> p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(this::convertirADTOResponse)
                .toList();
    }

    public EmpleadoDTO listarEmpleado(Long id) throws EmpleadoNoEncontradoException {
        Empleado empleado = iEmpleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));

        return convertirADTOResponse(empleado);
    }

    public EmpleadoDTO buscarEmpleadoPorEmail(String email){
        Empleado empleado = iEmpleadoRepository.findByEmail(email);
        return convertirADTOResponse(empleado);
    }

    public List<EmpleadoDTO> buscarEmpleadoPorNombre(String nombre){
        return iEmpleadoRepository.findByNombre(nombre)
                .stream()
                .filter(p->p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(this::convertirADTOResponse)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO buscarEmpleadoPorTelefono(String telefono){
        Empleado empleado = iEmpleadoRepository.findByTelefono(telefono);
        return convertirADTOResponse(empleado);
    }

    public EmpleadoDTO buscarEmpleadoPorDni(Long dni){
        Empleado empleado = iEmpleadoRepository.findByDni(dni);
        return convertirADTOResponse(empleado);
    }

    public EmpleadoDTO buscarEmpleadoPorLegajo(Long legajo){
        Empleado empleado = iEmpleadoRepository.findByLegajo(legajo);
        return convertirADTOResponse(empleado);
    }

    public CrearEmpleadoDTO crearEmpleado(CrearEmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setEmail(dto.email());
        empleado.setContrasenia(dto.dni().toString());
        empleado.setNombre(dto.nombre());
        empleado.setTelefono(dto.telefono());
        empleado.setDni(dto.dni());
        empleado.setEEstadoActividad(EEstadoActividad.ACTIVO);
        empleado = iEmpleadoRepository.save(empleado);

        return convertirADTORequest(empleado);
    }

    public CrearEmpleadoDTO modificarEmpleado(Long id, CrearEmpleadoDTO dto) throws EmpleadoNoEncontradoException {
        Empleado empleado = iEmpleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));

        empleado.setEmail(dto.email());
        empleado.setContrasenia(passwordEncoder.encode(dto.contrasenia()));
        empleado.setNombre(dto.nombre());
        empleado.setTelefono(dto.telefono());
        empleado.setDni(dto.dni());

        Empleado empleadoActualizado = iEmpleadoRepository.save(empleado);

        return convertirADTORequest(empleadoActualizado);
    }

    public void eliminarEmpleado(Long id) throws EmpleadoNoEncontradoException {
        Empleado e = iEmpleadoRepository.findById(id).orElseThrow(()-> new EmpleadoNoEncontradoException("Empleado no encontrado para eliminar"));
        e.setEEstadoActividad(EEstadoActividad.CANCELADO);
        iEmpleadoRepository.save(e);
    }

    private EmpleadoDTO convertirADTOResponse(Empleado empleado) {
        return new EmpleadoDTO(
                empleado.getEmail(),
                empleado.getNombre(),
                empleado.getTelefono(),
                empleado.getDni(),
                empleado.getLegajo()
        );
    }

    private CrearEmpleadoDTO convertirADTORequest(Empleado empleado) {
        return new CrearEmpleadoDTO(
                empleado.getEmail(),
                empleado.getContrasenia(),
                empleado.getNombre(),
                empleado.getTelefono(),
                empleado.getDni()
        );
    }
}

