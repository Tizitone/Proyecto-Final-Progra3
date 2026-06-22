package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.CrearRegistroDTO;
import com.metalurgica1.metalurgica1.DTO.EmpleadoModeloDTO;
import com.metalurgica1.metalurgica1.DTO.RegistroDTO;
import com.metalurgica1.metalurgica1.DTO.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.*;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import com.metalurgica1.metalurgica1.modelo.enums.EProceso;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.RegistroNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  RegistroService {
    private final IRegistroRepository iRegistroRepository;
    private final IClienteRepository iClienteRepository;
    private final ITareaRepository iTareaRepository;
    private EmpleadoModeloService empleadoModeloService;

    public RegistroService(IRegistroRepository iRegistroRepository,EmpleadoModeloService empleadoModeloService, IClienteRepository iClienteRepository, ITareaRepository iTareaRepository, IEmpleadoRepository iEmpleadoRepository) {
        this.iRegistroRepository = iRegistroRepository;
        this.iClienteRepository = iClienteRepository;
        this.iTareaRepository = iTareaRepository;
        this.empleadoModeloService = empleadoModeloService;
    }

    private RegistroDTO convertirADTO(Registro r){
        return new RegistroDTO(r.getId(),r.getTitulo(),r.getTarea().getId(),
                r.getCliente().getIdCliente(),r.getEProceso(),listarIdEmpleados(r), r.getPublicado());
    }

    public List<RegistroDTO> listarRegistros(){
        return iRegistroRepository.
                findAll()
                .stream()
                .filter(p-> p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(r -> new RegistroDTO(
                        r.getId(),
                        r.getTitulo(),
                        r.getTarea().getId(),
                        r.getCliente().getIdCliente(),
                        r.getEProceso(),
                        listarIdEmpleados(r),
                        r.getPublicado()))
                .collect(Collectors.toList());
    }
    public List<RegistroDTO> listarRegistrosClientes(Long id)
    {
        return iRegistroRepository
                .findAll()
                .stream()
                .filter(p->(p.getEEstadoActividad() == EEstadoActividad.ACTIVO) && (p.getId().equals(id)))
                .map(r -> new RegistroDTO(
                        r.getId(),
                        r.getTitulo(),
                        r.getTarea().getId(),
                        r.getCliente().getIdCliente(),
                        r.getEProceso(),
                        listarIdEmpleados(r),
                        r.getPublicado()))
                .toList();
    }
    public List<TareaDTO> listarTareasRegistro(Long id)
    {
        return iTareaRepository
                .findAll()
                .stream()
                .filter(p->(p.getEEstadoActividad() == EEstadoActividad.ACTIVO) && (p.getId().equals(id)))
                .map(t-> new TareaDTO(
                        t.getId(),
                        t.getCategorias(),
                        t.getFechaDeEntrega(),
                        t.getFechaDeRegistro(),
                        t.getDescripcionMaterial(),
                        t.getDescripcionGeneral()

                ))
                .toList();
    }

    public RegistroDTO buscarRegistroPorId(Long id) throws RegistroNoEncontradoException {
        Registro r = iRegistroRepository.findById(id)
                .orElseThrow(()-> new RegistroNoEncontradoException(id));

        return new RegistroDTO(
                r.getId(),
                r.getTitulo(),
                r.getTarea().getId(),
                r.getCliente().getIdCliente(),
                r.getEProceso(),
                listarIdEmpleados(r),
                r.getPublicado());
    }

    public List<RegistroDTO> buscarRegistroPorTitulo(String titulo){
        return iRegistroRepository.findByTitulo(titulo)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<RegistroDTO> buscarPublicados(){
        return iRegistroRepository.findByPublicadoTrue()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<RegistroDTO> buscarPorProceso(EProceso proceso){
        return iRegistroRepository.findByeProceso(proceso)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    private List<Empleado_modelo> listarEmpleados(CrearRegistroDTO dto) {
        return dto.participantesId().stream()
                .map(id -> empleadoModeloService.buscarEmpleadoPorId(id)) // Devuelve DTO
                .map(this::convertirDtoAEntidad) // Convierte DTO a Entidad
                .toList();
    }
    private List<Long> listarIdEmpleados(Registro r)
    {
        return r.getParticipantes().stream()
                .map(Empleado_modelo::getLegajo)
                .toList();
    }

    private Empleado_modelo convertirDtoAEntidad(EmpleadoModeloDTO dto) {
        Empleado_modelo empleado;

        if (dto.eEtiquetaDeAcceso() == EEtiquetaDeAcceso.GERENTE) {
            empleado = new Empleado_Gerente();
        } else {
            empleado = new Empleado();
        }
        empleado.setLegajo(dto.legajo());
        empleado.setNombre(dto.nombre());
        empleado.setEmail(dto.email());
        empleado.setTelefono(dto.telefono());
        empleado.setDni(dto.dni());
        empleado.setEEstadoActividad(EEstadoActividad.ACTIVO);

        return empleado;
    }


    public CrearRegistroDTO crearRegistro(CrearRegistroDTO dto) throws TareaNoEncontradaExeption, ClienteNoEncontradoException {
        Registro registro = new Registro();

        Tarea t = iTareaRepository.findById(dto.tareaId())
                .orElseThrow(()-> new TareaNoEncontradaExeption(dto.tareaId()));

        Cliente c = iClienteRepository.findById(dto.clienteId())
                .orElseThrow(()-> new ClienteNoEncontradoException(dto.clienteId()));

        registro.setEProceso(EProceso.ESPERA);

        registro.setTitulo(dto.titulo());

        registro.setTarea(t);

        registro.setCliente(c);

        registro.setEEstadoActividad(EEstadoActividad.ACTIVO);

        registro.setParticipantes(listarEmpleados(dto));

        Registro nuevoRegistro = iRegistroRepository.save(registro);

        return new CrearRegistroDTO(
                nuevoRegistro.getTitulo(),
                nuevoRegistro.getTarea().getId(),
                nuevoRegistro.getCliente().getIdCliente(),
                nuevoRegistro.getEProceso(),
                listarIdEmpleados(nuevoRegistro),
                nuevoRegistro.getPublicado());
    }

    public CrearRegistroDTO modificarRegistro(Long id, CrearRegistroDTO dto) throws RegistroNoEncontradoException, TareaNoEncontradaExeption, ClienteNoEncontradoException {
        Registro registro = iRegistroRepository.findById(id)
                .orElseThrow(()-> new RegistroNoEncontradoException(id));

        Tarea t = iTareaRepository.findById(dto.tareaId())
                .orElseThrow(()-> new TareaNoEncontradaExeption(dto.tareaId()));

        Cliente c = iClienteRepository.findById(dto.clienteId())
                .orElseThrow(()-> new ClienteNoEncontradoException(dto.clienteId()));

        registro.setTitulo(dto.titulo());

        registro.setEProceso(dto.proceso());

        registro.setTarea(t);

        registro.setCliente(c);


        if(dto.participantesId() != null && !dto.participantesId().isEmpty()) {
            List<Empleado_modelo> participantes = listarEmpleados(dto);

            registro.setParticipantes(participantes);
        }

        Registro nuevoRegistro = iRegistroRepository.save(registro);


        return new CrearRegistroDTO(
                nuevoRegistro.getTitulo(),
                nuevoRegistro.getTarea().getId(),
                nuevoRegistro.getCliente().getIdCliente(),
                nuevoRegistro.getEProceso(),
                listarIdEmpleados(nuevoRegistro),
                nuevoRegistro.getPublicado());
    }

    public RegistroDTO publicarRegistro(Long id){
        Registro registro = iRegistroRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Registro no encontrado"));
        registro.setPublicado(true);

        return convertirADTO(registro);
    }

    public void eliminarRegistro(Long id) throws RegistroNoEncontradoException {
       Registro r = iRegistroRepository.findById(id).orElseThrow(()-> new RegistroNoEncontradoException(id));
       r.setEEstadoActividad(EEstadoActividad.CANCELADO);
       iRegistroRepository.save(r);
    }
}
