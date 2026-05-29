package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.dto.RegistroDTO;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegistroService {
    private final IRegistroRepository iRegistroRepository;
    private final IClienteRepository iClienteRepository;
    private final ITareaRepository iTareaRepository;
    private final IEmpleadoRepository iEmpleadoRepository;

    public RegistroService(IRegistroRepository iRegistroRepository, IClienteRepository iClienteRepository, ITareaRepository iTareaRepository, IEmpleadoRepository iEmpleadoRepository) {
        this.iRegistroRepository = iRegistroRepository;
        this.iClienteRepository = iClienteRepository;
        this.iTareaRepository = iTareaRepository;
        this.iEmpleadoRepository = iEmpleadoRepository;
    }

    public List<Registro> listarRegistros(){
        return iRegistroRepository.findAll();
    }

    public Registro buscarPorId(Long id){
        return iRegistroRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado"));
    }

    public Registro crearRegistro(RegistroDTO dto){
        Registro registro = new Registro();

        registro.setTitulo(dto.titulo());

        registro.setTarea(iTareaRepository.findById(dto.tareaId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada")));

        registro.setCliente(iClienteRepository.findById(dto.clienteId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));

        if(dto.participantesId() != null && !dto.participantesId().isEmpty()) {
            List<Empleado> participantes = dto.participantesId().stream()
                    .map(empId -> iEmpleadoRepository.findById(empId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado")))
                    .toList();
            registro.setParticipantes(participantes);
        }

        return iRegistroRepository.save(registro);
    }

    public Registro modificarRegistro(Long id, RegistroDTO dto) {
        Registro registro = iRegistroRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado"));
        registro.setTitulo(dto.titulo());
        registro.setTarea(iTareaRepository.findById(dto.tareaId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada")));
        registro.setCliente(iClienteRepository.findById(dto.clienteId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));
        if(dto.participantesId() != null && !dto.participantesId().isEmpty()) {
            List<Empleado> participantes = dto.participantesId().stream()
                    .map(empId -> iEmpleadoRepository.findById(empId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado")))
                    .toList();
            registro.setParticipantes(participantes);
        }

        return iRegistroRepository.save(registro);
    }

    public void eliminarRegistro(Long id){
        if(!iRegistroRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Registro no encontrado");
        }
        iRegistroRepository.deleteById(id);
    }
}
