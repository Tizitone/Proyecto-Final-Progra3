package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.*;
import com.metalurgica1.metalurgica1.modelo.*;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.modelo.enums.EProceso;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.RegistroNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class  RegistroService {
    private final IRegistroRepository iRegistroRepository;
    private final IClienteRepository iClienteRepository;
    private final ITareaRepository iTareaRepository;
    private EmpleadoModeloService empleadoModeloService;

    @PersistenceContext
    private EntityManager entityManager;

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
                .filter(p->(p.getEEstadoActividad() == EEstadoActividad.ACTIVO) && (p.getCliente().getIdCliente().equals(id)))
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
    public List<Empleado_modelo> listarEmpleados(){
        return iRegistroRepository.findAll().stream().flatMap(registro -> registro.getParticipantes().stream()).toList();

    }
    public List<TareaDTO> listarTareasRegistro(Long id)
    {
        return iRegistroRepository.findById(id)
                .stream()
                .filter(reg -> reg.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(Registro::getTarea)
                .filter(tarea -> tarea != null && tarea.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(t -> new TareaDTO(
                        t.getId(),
                        t.getCategorias(),
                        t.getTitulo(),
                        t.getFechaDeEntrega(),
                        t.getFechaDeRegistro(),
                        t.getDescripcionMaterial(),
                        t.getDescripcionGeneral(),
                        t.getCantidad()
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
    /**
     * Resuelve los legajos como entidades administradas por Hibernate. Antes se
     * construían objetos nuevos solo con el ID, lo que dejaba el historial y el
     * resto de los datos del empleado fuera del contexto de persistencia.
     */
    private List<Empleado_modelo> listarEmpleados(CrearRegistroDTO dto) {
        return dto.participantesId().stream()
                .map(this::buscarEmpleadoGestionado)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Empleado_modelo buscarEmpleadoGestionado(Long legajo) {
        Empleado_modelo empleado = entityManager.find(Empleado_modelo.class, legajo);

        if (empleado == null) {
            throw new RuntimeException("Empleado con legajo " + legajo + " no encontrado");
        }

        return empleado;
    }

    private List<Long> listarIdEmpleados(Registro r)
    {
        return r.getParticipantes().stream()
                .map(Empleado_modelo::getLegajo)
                .toList();
    }

    /**
     * El historial de tareas del empleado vive en otra tabla (empleado_historial)
     * y no se actualiza sola cuando cambian los participantes de un registro.
     * Al asignar un registro se agrega su tarea al historial de cada empleado
     * participante. La operación es idempotente para no duplicar tareas.
     */
    private void sincronizarHistorial(List<Empleado_modelo> participantes, Tarea tarea) {
        for (Empleado_modelo participante : participantes) {
            if (!(participante instanceof Empleado empleado)) {
                continue; // Los gerentes no tienen historial de tareas.
            }

            List<Tarea> historial = empleado.getHistorial();
            if (historial == null) {
                historial = new ArrayList<>();
                empleado.setHistorial(historial);
            }

            boolean yaAsignada = historial.stream()
                    .anyMatch(t -> t != null && t.getId() != null && t.getId().equals(tarea.getId()));

            if (!yaAsignada) {
                historial.add(tarea);
            }
        }
    }

    @Transactional
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
        sincronizarHistorial(registro.getParticipantes(), t);

        Registro nuevoRegistro = iRegistroRepository.save(registro);

        return new CrearRegistroDTO(
                nuevoRegistro.getTitulo(),
                nuevoRegistro.getTarea().getId(),
                nuevoRegistro.getCliente().getIdCliente(),
                nuevoRegistro.getEProceso(),
                listarIdEmpleados(nuevoRegistro),
                nuevoRegistro.getPublicado());
    }

    @Transactional
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
            sincronizarHistorial(participantes, t);
        } else {
            // Los perfiles envían la lista vacía cuando solo cambia el estado:
            // se conservan los participantes y se asegura el historial de la tarea.
            sincronizarHistorial(new ArrayList<>(registro.getParticipantes()), t);
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
