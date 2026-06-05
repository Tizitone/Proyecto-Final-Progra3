package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.CrearGerenteDTO;
import com.metalurgica1.metalurgica1.DTO.GerenteDTO;
import com.metalurgica1.metalurgica1.modelo.Empleado_Gerente;
import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import com.metalurgica1.metalurgica1.repositorio.IGerenteRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;

import java.util.List;
import java.util.stream.Collectors;

public class GerenteService {

    private final IGerenteRepository iGerenteRepository;

    public GerenteService(IGerenteRepository iGerenteRepository) {
        this.iGerenteRepository = iGerenteRepository;
    }

    public List<GerenteDTO> listarTodosGerentes() {
        return iGerenteRepository.findAll()
                .stream()
                .map(this::convertirADTOResponse)
                .toList();
    }

    public GerenteDTO listarGerente(Long id) throws EmpleadoNoEncontradoException {
        Empleado_Gerente gerente = iGerenteRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado."));

        return convertirADTOResponse(gerente);
    }

    public GerenteDTO buscarGerentePorMail(String email){
        Empleado_Gerente gerente = iGerenteRepository.findByEmail(email);
        return convertirADTOResponse(gerente);
    }

    public List<GerenteDTO> buscarGerentePorNombre(String nombre){
        return iGerenteRepository.findByNombre(nombre)
                .stream()
                .map(this::convertirADTOResponse)
                .collect(Collectors.toList());
    }

    public GerenteDTO buscarGerentePorTelefono(String telefono){
        Empleado_Gerente gerente = iGerenteRepository.findByTelefono(telefono);
        return convertirADTOResponse(gerente);
    }

    public GerenteDTO buscarGerentePorDni(Long dni){
        Empleado_Gerente gerente = iGerenteRepository.findByDni(dni);
        return convertirADTOResponse(gerente);
    }

    public GerenteDTO buscarGerentePorLegajo(Long legajo){
        Empleado_Gerente gerente = iGerenteRepository.findByLegajo(legajo);
        return convertirADTOResponse(gerente);
    }

    public CrearGerenteDTO crearGerente(CrearGerenteDTO dto) {
        Empleado_Gerente gerente = new Empleado_Gerente();
        gerente.setEmail(dto.email());
        gerente.setContrasenia(dto.contrasenia());
        gerente.setNombre(dto.nombre());
        gerente.setTelefono(dto.telefono());
        gerente.setDni(dto.dni());
        gerente.setEtiquetaDeAcceso(EEtiquetaDeAcceso.GERENTE);

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

    public void eliminarEmpleado(Long id) throws EmpleadoNoEncontradoException {
        if(!iGerenteRepository.existsById(id))
            throw new EmpleadoNoEncontradoException("Gerente a eliminar no encontrado.");

        iGerenteRepository.deleteById(id);
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

