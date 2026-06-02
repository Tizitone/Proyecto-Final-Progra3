package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.AdministradorDTO;
import com.metalurgica1.metalurgica1.DTO.CrearAdministradorDTO;
import com.metalurgica1.metalurgica1.modelo.Administrador;
import com.metalurgica1.metalurgica1.repositorio.IAdministradorRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.AdministradorNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    @Autowired
    private IAdministradorRepository iAdministradorRepository;

    public AdministradorService(IAdministradorRepository iAdministradorRepository) {
        this.iAdministradorRepository = iAdministradorRepository;
    }

    public List<AdministradorDTO> listarAdministradores()
    {
        return iAdministradorRepository.
                findAll().
                stream().
                map(p-> new AdministradorDTO(p.getEmail(),p.getNombre(),p.getTelefono(),p.getDni())).collect(Collectors.toList());
    }
    public AdministradorDTO buscarAdministrador(Long id) throws AdministradorNoEncontradoException {
        Administrador a = iAdministradorRepository.findById(id).orElseThrow(()-> new AdministradorNoEncontradoException("El administrador con la id:"+id+" no se encontro para modificar"));
        return new AdministradorDTO(a.getEmail(),a.getNombre(),a.getTelefono(),a.getDni());
    }

    public CrearAdministradorDTO crearAdministrador(CrearAdministradorDTO dto)
    {
        Administrador a = new Administrador();
        a.setNombre(dto.nombre());
        a.setEmail(dto.email());
        a.setContrasenia(dto.contrasenia());
        a.setTelefono(dto.telefono());
        a.setDni(dto.dni());

        Administrador nuevoAdministrador = iAdministradorRepository.save(a);

        return new CrearAdministradorDTO(
                nuevoAdministrador.getEmail(),
                nuevoAdministrador.getContrasenia(),
                nuevoAdministrador.getNombre(),
                nuevoAdministrador.getTelefono(),
                nuevoAdministrador.getDni());
    }
    public AdministradorDTO modificarAdministrador(Long id, AdministradorDTO dto) throws AdministradorNoEncontradoException {
        Administrador a = iAdministradorRepository.findById(id).orElseThrow(()-> new AdministradorNoEncontradoException("El administrador con la id:"+id+" no se encontro para modificar"));
        a.setNombre(dto.nombre());
        a.setEmail(dto.email());
        a.setTelefono(dto.telefono());
        a.setDni(dto.dni());

        Administrador nuevoAdministrador = iAdministradorRepository.save(a);

        return new AdministradorDTO(
                nuevoAdministrador.getEmail(),
                nuevoAdministrador.getNombre(),
                nuevoAdministrador.getTelefono(),
                nuevoAdministrador.getDni());
    }

}
