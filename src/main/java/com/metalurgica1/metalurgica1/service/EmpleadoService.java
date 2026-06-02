package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.modelo.Cliente;
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

    public EmpleadoDTO listarEmpleado(Long id) throws EmpleadoNoEncontradoException {
        Empleado empleado = iEmpleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));

        return convertirADTOResponse(empleado);
    }

    public Empleado buscarEmpleadoPorEmail(String email){
        return iEmpleadoRepository.findByEmail(email);
    }

    public List<Empleado> buscarEmpleadoPorNombre(String nombre){
        return iEmpleadoRepository.findByNombre(nombre);
    }

    public Empleado buscarEmpleadoPorTelefono(String telefono){
        return iEmpleadoRepository.findByTelefono(telefono);
    }

    public Empleado buscarEmpleadoPorDni(Long dni){
        return iEmpleadoRepository.findByDni(dni);
    }

    public Empleado buscarEmpleadoPorLegajo(Long legajo){
        return iEmpleadoRepository.findByLegajo(legajo);
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

    public CrearEmpleadoDTO modificarEmpleado(Long id, CrearEmpleadoDTO dto) throws EmpleadoNoEncontradoException {
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

    public void eliminarEmpleado(Long id) throws EmpleadoNoEncontradoException {
        Empleado e = iEmpleadoRepository.findById(id).orElseThrow(()-> new EmpleadoNoEncontradoException("Empleado no encontrado para eliminar"));

        iEmpleadoRepository.delete(e);
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

