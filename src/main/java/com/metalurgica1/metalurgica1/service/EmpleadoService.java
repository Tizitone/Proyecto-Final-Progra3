package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.DTO.EmpleadoDTO;
import com.metalurgica1.metalurgica1.DTO.CrearEmpleadoDTO;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class EmpleadoService {

    private final IEmpleadoRepository iEmpleadoRepository;

    public EmpleadoService(IEmpleadoRepository iEmpleadoRepository) {
        this.iEmpleadoRepository = iEmpleadoRepository;
    }

    public List<EmpleadoDTO> listarTodosEmpleados() {
        return iEmpleadoRepository.findAll()
                .stream()
                .map(this::convertirADTOResponse)
                .toList();
    }

    public EmpleadoDTO listarEmpleado(Long id){
        Empleado empleado = iEmpleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));

        return convertirADTOResponse(empleado);
    }

    public CrearEmpleadoDTO crearEmpleado(CrearEmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setEmail(dto.email());
        empleado.setContrasenia(dto.contrasenia());
        empleado.setNombre(dto.nombre());
        empleado.setTelefono(dto.telefono());
        empleado.setDni(dto.dni());
        empleado.setEtiquetaDeAcceso(EEtiquetaDeAcceso.EMPLEADO);

        empleado = iEmpleadoRepository.save(empleado);

        return convertirADTORequest(empleado);
    }

    public CrearEmpleadoDTO modificarEmpleado(Long id, CrearEmpleadoDTO dto) {
        Empleado empleado = iEmpleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));

        empleado.setEmail(dto.email());
        empleado.setContrasenia(dto.contrasenia());
        empleado.setNombre(dto.nombre());
        empleado.setTelefono(dto.telefono());
        empleado.setDni(dto.dni());

        Empleado empleadoActualizado = iEmpleadoRepository.save(empleado);

        return convertirADTORequest(empleadoActualizado);
    }

    public void eliminarEmpleado(Long id){
        if(!iEmpleadoRepository.existsById(id))
            throw new EmpleadoNoEncontradoException("Empleado no encontrado.");

        iEmpleadoRepository.deleteById(id);
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

