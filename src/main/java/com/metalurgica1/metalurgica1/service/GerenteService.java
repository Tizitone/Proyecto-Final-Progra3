package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.*;
import com.metalurgica1.metalurgica1.modelo.Empleado_Gerente;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.*;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenteService extends MetodosGestion {

    private final IGerenteRepository iGerenteRepository;



    public GerenteService(IGerenteRepository iGerenteRepository) {
        this.iGerenteRepository = iGerenteRepository;

    }

    public List<GerenteDTO> listarTodosGerentes() {
        return iGerenteRepository.findAll()
                .stream()
                .filter(p-> p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(this::convertirADTOResponse)
                .toList();
    }

    public GerenteDTO listarGerente(Long id) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));
        if(gerente.getEEstadoActividad() == EEstadoActividad.CANCELADO)
        {
            throw new EmpleadoNoEncontradoException("Empleado no encontrado");
        }

        return convertirADTOResponse(gerente);
    }

    public GerenteDTO buscarGerentePorMail(String email) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findByEmail(email);
        if(gerente.getEEstadoActividad() == EEstadoActividad.CANCELADO)
        {
            throw new EmpleadoNoEncontradoException("Empleado no encontrado");
        }
        return convertirADTOResponse(gerente);
    }

    public List<GerenteDTO> buscarGerentePorNombre(String nombre){
        return iGerenteRepository.findByNombre(nombre)
                .stream()
                .filter(p->p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(this::convertirADTOResponse)
                .collect(Collectors.toList());
    }

    public GerenteDTO buscarGerentePorTelefono(String telefono) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findByTelefono(telefono);
        if(gerente.getEEstadoActividad() == EEstadoActividad.CANCELADO)
        {
            throw new EmpleadoNoEncontradoException("Gerente no encontrado");
        }
        return convertirADTOResponse(gerente);
    }

    public GerenteDTO buscarGerentePorDni(Long dni) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findByDni(dni);
        if(gerente.getEEstadoActividad() == EEstadoActividad.CANCELADO)
        {
            throw new EmpleadoNoEncontradoException("Gerente no encontrado");
        }
        return convertirADTOResponse(gerente);
    }

    public GerenteDTO buscarGerentePorLegajo(Long legajo) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findByLegajo(legajo);
        if(gerente.getEEstadoActividad() == EEstadoActividad.CANCELADO)
        {
            throw new EmpleadoNoEncontradoException("Gerente no encontrado");
        }
        return convertirADTOResponse(gerente);
    }

    public CrearGerenteDTO crearGerente(CrearGerenteDTO dto) {
        Empleado_Gerente gerente = new Empleado_Gerente();
        gerente.setEmail(dto.email());
        gerente.setContrasenia(dto.contrasenia());
        gerente.setNombre(dto.nombre());
        gerente.setTelefono(dto.telefono());
        gerente.setDni(dto.dni());
        gerente.setEEstadoActividad(EEstadoActividad.ACTIVO);

        return convertirADTORequest(iGerenteRepository.save(gerente));
    }

    public CrearGerenteDTO modificarGerente(Long id, CrearGerenteDTO dto) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Gerente no encontrado."));

        gerente.setEmail(dto.email());
        gerente.setContrasenia(dto.contrasenia());
        gerente.setNombre(dto.nombre());
        gerente.setTelefono(dto.telefono());
        gerente.setDni(dto.dni());

        return convertirADTORequest(iGerenteRepository.save(gerente));
    }

    public void eliminarGerente(Long id) throws EmpleadoNoEncontradoException {
        Empleado_Gerente e = iGerenteRepository.findById(id).orElseThrow(()-> new EmpleadoNoEncontradoException(id));
        e.setEEstadoActividad(EEstadoActividad.CANCELADO);
        iGerenteRepository.save(e);
    }

    private GerenteDTO convertirADTOResponse(Empleado_Gerente gerente) {
        return new GerenteDTO(
                gerente.getEmail(),
                gerente.getNombre(),
                gerente.getTelefono(),
                gerente.getDni(),
                gerente.getLegajo()
        );
    }

    private CrearGerenteDTO convertirADTORequest(Empleado_Gerente gerente) {
        return new CrearGerenteDTO(
                gerente.getEmail(),
                gerente.getContrasenia(),
                gerente.getNombre(),
                gerente.getTelefono(),
                gerente.getDni()
        );
    }




}

