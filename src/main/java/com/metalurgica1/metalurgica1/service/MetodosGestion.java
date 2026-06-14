package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.dto_temp.*;
import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Registro;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import com.metalurgica1.metalurgica1.modelo.interfaces.IGestion;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IRegistroRepository;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.RegistroNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

//metodos que implementaran gerente y administrador
public abstract class MetodosGestion implements IGestion {

    @Autowired
    private IEmpleadoRepository iEmpleadoRepository;

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private ITareaRepository iTareaRepository;

    @Autowired
    private IRegistroRepository iRegistroRepository;

    @Override
    public List<ClienteDTO> visualizarClientes() {
        return iClienteRepository
                .findAll()
                .stream()
                .map(p-> new ClienteDTO(p.getEmail(),p.getNombre(),p.getTelefono(),p.getDni(),p.getIdCliente()))
                .toList();
    }

    @Override
    public List<EmpleadoModeloDTO> visualizarEmpleados() {
        return iEmpleadoRepository.findAll().stream()
                .filter(p-> p.getEtiquetaDeAcceso().equals(EEtiquetaDeAcceso.EMPLEADO))
                .map(p-> new EmpleadoModeloDTO(p.getLegajo(),p.getEmail(),p.getNombre(),p.getTelefono(),p.getDni()))
                .toList();
    }

    @Override
    public List<TareaDTO> visualizarTareas() {
        return iTareaRepository
                .findAll()
                .stream()
                .map(p-> new TareaDTO(p.getCategorias(),p.getFechaDeEntrega(),p.getFechaDeRegistro(),p.getDescripcionMaterial(),p.getDescripcionGeneral()))
                .toList();
    }

    @Override
    public List<RegistroDTO> visualizarRegistros() {
        return iRegistroRepository.findAll().stream()
                .map(p-> new RegistroDTO(p.getId(),p.getTitulo(),p.getTarea().getId(),p.getCliente().getIdCliente(),p.getEProceso(),p.getParticipantes(),p.getPublicado()))
                .toList();
    }

    @Override
    public EmpleadoDTO buscarEmpleado(Long legajo) {
        Empleado e = iEmpleadoRepository.findByLegajo(legajo);
        return new EmpleadoDTO(e.getEmail(),e.getNombre(),e.getTelefono(),e.getDni(),e.getLegajo());
    }

    @Override
    public TareaDTO buscarTarea(Long id) throws TareaNoEncontradaExeption {
        Tarea t = iTareaRepository.findById(id).orElseThrow(()-> new TareaNoEncontradaExeption(id));
        return new TareaDTO(t.getCategorias(),t.getFechaDeEntrega(),t.getFechaDeRegistro(),t.getDescripcionMaterial(),t.getDescripcionGeneral());
    }

    @Override
    public ClienteDTO buscarCliente(Long dni) {
        Cliente c = iClienteRepository.findByDni(dni);
        return new ClienteDTO(c.getEmail(),c.getNombre(),c.getTelefono(),c.getDni(), c.getIdCliente());
    }

    @Override
    public RegistroDTO buscarRegistro(Long id) throws RegistroNoEncontradoException {
        Registro r = iRegistroRepository.findById(id).orElseThrow(()-> new RegistroNoEncontradoException(id));
        return new RegistroDTO(r.getId(),r.getTitulo(),r.getTarea().getId(),r.getCliente().getIdCliente(),r.getEProceso(),r.getParticipantes(),r.getPublicado());
    }

    @Override
    public ClienteDTO crearCliente(CrearClienteDTO dto) {
        Cliente c = new Cliente();
        c.setNombre(dto.nombre());
        c.setDni(dto.dni());
        c.setEmail(dto.email());
        c.setTelefono(dto.telefono());
        c.setContrasenia(dto.contrasenia());

        Cliente nuevoCliente = iClienteRepository.save(c);

        return new ClienteDTO(nuevoCliente.getEmail(), nuevoCliente.getNombre(), nuevoCliente.getTelefono(), nuevoCliente.getDni(), nuevoCliente.getIdCliente());
    }

    @Override
    public TareaDTO crearTarea(CrearTareaDTO dto) {
        Tarea t = new Tarea();
        t.setCategorias(dto.categorias());
        t.setFechaDeRegistro(LocalDateTime.now());
        t.setFechaDeEntrega(dto.fechaDeEntrega());
        t.setDescripcionMaterial(dto.descripcionMaterial());
        t.setDescripcionGeneral(dto.descripcionGeneral());

        Tarea nuevaTarea = iTareaRepository.save(t);
        return new TareaDTO(nuevaTarea.getCategorias(), nuevaTarea.getFechaDeEntrega(), nuevaTarea.getFechaDeRegistro(), nuevaTarea.getDescripcionMaterial(), nuevaTarea.getDescripcionGeneral());
    }

    @Override
    public RegistroDTO crearRegistro(RegistroDTO dto) throws ClienteNoEncontradoException, TareaNoEncontradaExeption {
        Registro r = new Registro();
        Cliente c = iClienteRepository.findById(dto.clienteId()).orElseThrow(()-> new ClienteNoEncontradoException(dto.id()));
        Tarea t = iTareaRepository.findById(dto.tareaId()).orElseThrow(()->new TareaNoEncontradaExeption(dto.tareaId()));
        r.setTitulo(dto.titulo());
        r.setCliente(c);
        r.setParticipantes(dto.participantesId());
        r.setEProceso(dto.proceso());
        r.setPublicado(dto.publicado());

        Registro nuevoRegistro = iRegistroRepository.save(r);

        return new RegistroDTO(nuevoRegistro.getId(), nuevoRegistro.getTitulo(), nuevoRegistro.getCliente().getIdCliente(),nuevoRegistro.getTarea().getId(),nuevoRegistro.getEProceso(),nuevoRegistro.getParticipantes(),nuevoRegistro.getPublicado());
    }

    @Override
    public EmpleadoDTO modificarEmpleado(EmpleadoDTO dto, Long legajo) {
        Empleado e = iEmpleadoRepository.findByLegajo(legajo);
        e.setNombre(dto.nombre());
        e.setEmail(dto.email());
        e.setTelefono(dto.telefono());
        e.setDni(dto.dni());

        Empleado nuevoEmpleado = iEmpleadoRepository.save(e);

        return new EmpleadoDTO(nuevoEmpleado.getEmail(), nuevoEmpleado.getNombre(), nuevoEmpleado.getTelefono(), nuevoEmpleado.getDni(), nuevoEmpleado.getLegajo());
    }

    @Override
    public ClienteDTO modificarCliente(ClienteDTO dto, Long id) throws ClienteNoEncontradoException {
        Cliente c = iClienteRepository.findById(id).orElseThrow(()-> new ClienteNoEncontradoException(id));
        c.setNombre(dto.nombre());
        c.setDni(dto.dni());
        c.setEmail(dto.email());
        c.setTelefono(dto.telefono());

        Cliente nuevoCliente = iClienteRepository.save(c);

        return new ClienteDTO(nuevoCliente.getEmail(), nuevoCliente.getNombre(), nuevoCliente.getTelefono(), nuevoCliente.getDni(), nuevoCliente.getIdCliente());
    }

    @Override
    public RegistroDTO modificarRegistro(RegistroDTO dto, Long id) throws RegistroNoEncontradoException, ClienteNoEncontradoException, TareaNoEncontradaExeption {
        Registro r = iRegistroRepository.findById(id).orElseThrow(()-> new RegistroNoEncontradoException(id));
        Cliente c = iClienteRepository.findById(dto.clienteId()).orElseThrow(()-> new ClienteNoEncontradoException(dto.id()));
        Tarea t = iTareaRepository.findById(dto.tareaId()).orElseThrow(()->new TareaNoEncontradaExeption(dto.tareaId()));
        r.setTitulo(dto.titulo());
        r.setCliente(c);
        r.setParticipantes(dto.participantesId());
        r.setEProceso(dto.proceso());
        r.setPublicado(dto.publicado());

        Registro nuevoRegistro = iRegistroRepository.save(r);

        return new RegistroDTO(nuevoRegistro.getId(), nuevoRegistro.getTitulo(), nuevoRegistro.getCliente().getIdCliente(),nuevoRegistro.getTarea().getId(),nuevoRegistro.getEProceso(),nuevoRegistro.getParticipantes(),nuevoRegistro.getPublicado());
    }

    @Override
    public TareaDTO modificarTarea(TareaDTO dto, Long id) throws TareaNoEncontradaExeption {
        Tarea t = iTareaRepository.findById(id).orElseThrow(()-> new TareaNoEncontradaExeption(id));
        t.setCategorias(dto.categorias());
        t.setFechaDeRegistro(LocalDateTime.now());
        t.setFechaDeEntrega(dto.fechaDeEntrega());
        t.setDescripcionMaterial(dto.descripcionMaterial());
        t.setDescripcionGeneral(dto.descripcionGeneral());

        Tarea nuevaTarea = iTareaRepository.save(t);
        return new TareaDTO(nuevaTarea.getCategorias(), nuevaTarea.getFechaDeEntrega(), nuevaTarea.getFechaDeRegistro(), nuevaTarea.getDescripcionMaterial(), nuevaTarea.getDescripcionGeneral());
    }
}
