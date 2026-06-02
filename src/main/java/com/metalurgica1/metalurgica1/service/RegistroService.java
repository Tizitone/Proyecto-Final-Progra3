package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.EmpleadoDTO;
import com.metalurgica1.metalurgica1.DTO.RegistroDTO;
import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.RegistroNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroService {
    private final IRegistroRepository iRegistroRepository;
    private final IClienteRepository iClienteRepository;
    private final ITareaRepository iTareaRepository;

    public RegistroService(IRegistroRepository iRegistroRepository, IClienteRepository iClienteRepository, ITareaRepository iTareaRepository) {
        this.iRegistroRepository = iRegistroRepository;
        this.iClienteRepository = iClienteRepository;
        this.iTareaRepository = iTareaRepository;
    }

    public List<RegistroDTO> listarRegistros(){
        return iRegistroRepository.
                findAll()
                .stream()
                .map(r -> new RegistroDTO(
                        r.getId(),
                        r.getTitulo(),
                        r.getTarea().getId(),
                        r.getCliente().getIdCliente(),
                        r.getParticipantes()))
                .collect(Collectors.toList());
    }
    public List<EmpleadoDTO> listarEmpleadosEnRegistro(Long id) throws RegistroNoEncontradoException {
        Registro r = iRegistroRepository.findById(id)
                .orElseThrow(()-> new RegistroNoEncontradoException(id));
        List<EmpleadoDTO> participantes = new ArrayList<>();

        for(Empleado e : r.getParticipantes())
        {
            participantes.add(new EmpleadoDTO(e.getEmail(),e.getNombre(),e.getTelefono(),e.getDni(),e.getLegajo()));
        }
        return participantes;
    }

    public RegistroDTO buscarRegistroPorId(Long id) throws RegistroNoEncontradoException {
        Registro r = iRegistroRepository.findById(id)
                .orElseThrow(()-> new RegistroNoEncontradoException(id));

        return new RegistroDTO(
                r.getId(),
                r.getTitulo(),
                r.getTarea().getId(),
                r.getCliente().getIdCliente(),
                r.getParticipantes());
    }

    public RegistroDTO crearRegistro(RegistroDTO dto) throws TareaNoEncontradaExeption, ClienteNoEncontradoException {
        Registro registro = new Registro();

        Tarea t = iTareaRepository.findById(dto.tareaId())
                .orElseThrow(()-> new TareaNoEncontradaExeption(dto.tareaId()));

        Cliente c = iClienteRepository.findById(dto.clienteId())
                .orElseThrow(()-> new ClienteNoEncontradoException(dto.clienteId()));

        registro.setTitulo(dto.titulo());

        registro.setTarea(t);

        registro.setCliente(c);

        if(dto.participantesId() != null && !dto.participantesId().isEmpty()) {
            List<Empleado> participantes = dto.participantesId();

            registro.setParticipantes(participantes);
        }

        Registro nuevoRegistro = iRegistroRepository.save(registro);

        return new RegistroDTO(
                nuevoRegistro.getId(),
                nuevoRegistro.getTitulo(),
                nuevoRegistro.getTarea().getId(),
                nuevoRegistro.getCliente().getIdCliente(),
                nuevoRegistro.getParticipantes());
    }

    public RegistroDTO modificarRegistro(Long id, RegistroDTO dto) throws RegistroNoEncontradoException, TareaNoEncontradaExeption, ClienteNoEncontradoException {
        Registro registro = iRegistroRepository.findById(id)
                .orElseThrow(()-> new RegistroNoEncontradoException(id));

        Tarea t = iTareaRepository.findById(dto.tareaId())
                .orElseThrow(()-> new TareaNoEncontradaExeption(dto.tareaId()));

        Cliente c = iClienteRepository.findById(dto.clienteId())
                .orElseThrow(()-> new ClienteNoEncontradoException(dto.clienteId()));

        registro.setTitulo(dto.titulo());

        registro.setTarea(t);

        registro.setCliente(c);

        if(dto.participantesId() != null && !dto.participantesId().isEmpty()) {
            List<Empleado> participantes = dto.participantesId();

            registro.setParticipantes(participantes);
        }

        Registro nuevoRegistro = iRegistroRepository.save(registro);

        return new RegistroDTO(
                nuevoRegistro.getId(),
                nuevoRegistro.getTitulo(),
                nuevoRegistro.getTarea().getId(),
                nuevoRegistro.getCliente().getIdCliente(),
                nuevoRegistro.getParticipantes());
    }

    public void eliminarRegistro(Long id){
        if(!iRegistroRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Registro no encontrado");
        }
        iRegistroRepository.deleteById(id);
    }
}
