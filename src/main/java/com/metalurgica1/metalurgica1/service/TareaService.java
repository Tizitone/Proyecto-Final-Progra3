package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.CrearTareaDTO;
import com.metalurgica1.metalurgica1.DTO.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {
    private final ITareaRepository iTareaRepository;

    public TareaService(ITareaRepository iTareaRepository) {
        this.iTareaRepository = iTareaRepository;
    }

    private TareaDTO convertirADTO(Tarea t){
        return new TareaDTO(
                t.getId(),
                t.getCategorias(),
                t.getTitulo(),
                t.getFechaDeEntrega(),
                t.getFechaDeRegistro(),
                t.getDescripcionMaterial(),
                t.getDescripcionGeneral(),
                t.getCantidad()
        );
    }

    public List<TareaDTO> listarTareas(){
        return iTareaRepository
                .findAll()
                .stream()
                .filter(p-> p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                .map(t -> new TareaDTO(
                        t.getId(),
                        t.getCategorias(),
                        t.getTitulo(),
                        t.getFechaDeEntrega(),
                        t.getFechaDeRegistro(),
                        t.getDescripcionMaterial(),
                        t.getDescripcionGeneral(),
                        t.getCantidad()
                        )
                )
                .collect(Collectors.toList());
    }

    private Tarea buscarTarea(Long id) throws TareaNoEncontradaExeption {
        return iTareaRepository.findById(id)
                .orElseThrow(()-> new TareaNoEncontradaExeption(id));
    }

    public TareaDTO buscarPorId(Long id) throws TareaNoEncontradaExeption {
        Tarea t = buscarTarea(id);
        return new TareaDTO(
                t.getId(),
                t.getCategorias(),
                t.getTitulo(),
                t.getFechaDeEntrega(),
                t.getFechaDeRegistro(),
                t.getDescripcionMaterial(),
                t.getDescripcionGeneral(),
                t.getCantidad()
                );

    }
    public CrearTareaDTO crearTarea(CrearTareaDTO dto){
        Tarea tarea = new Tarea();

        tarea.setTitulo(dto.titulo());
        tarea.setCategorias(dto.categorias());
        tarea.setFechaDeRegistro(LocalDateTime.now());
        tarea.setFechaDeEntrega(dto.fechaDeEntrega());
        tarea.setDescripcionMaterial(dto.descripcionMaterial());
        tarea.setDescripcionGeneral(dto.descripcionGeneral());
        tarea.setCantidad(dto.cant());
        tarea.setEEstadoActividad(EEstadoActividad.ACTIVO);

        Tarea nuevaTarea = iTareaRepository.save(tarea);

        return new CrearTareaDTO(
                nuevaTarea.getTitulo(),
                nuevaTarea.getCategorias(),
                nuevaTarea.getFechaDeEntrega(),
                nuevaTarea.getDescripcionMaterial(),
                nuevaTarea.getDescripcionGeneral(),
                nuevaTarea.getCantidad()
        );
    }

    public CrearTareaDTO modificarTarea(Long id, CrearTareaDTO dto) throws TareaNoEncontradaExeption {
        Tarea tarea = buscarTarea(id);
        tarea.setTitulo(dto.titulo());
        tarea.setCategorias(dto.categorias());
        tarea.setFechaDeEntrega(dto.fechaDeEntrega());
        tarea.setDescripcionMaterial(dto.descripcionMaterial());
        tarea.setDescripcionGeneral(dto.descripcionGeneral());
        tarea.setCantidad(dto.cant());

        Tarea nuevaTarea = iTareaRepository.save(tarea);

        return new CrearTareaDTO(
                nuevaTarea.getTitulo(),
                nuevaTarea.getCategorias(),
                nuevaTarea.getFechaDeEntrega(),
                nuevaTarea.getDescripcionMaterial(),
                nuevaTarea.getDescripcionGeneral(),
                nuevaTarea.getCantidad()
        );
    }

    public void eliminarTarea(Long id) throws TareaNoEncontradaExeption {
        Tarea t = iTareaRepository.findById(id).orElseThrow(()-> new TareaNoEncontradaExeption(id));
        t.setEEstadoActividad(EEstadoActividad.CANCELADO);
        iTareaRepository.save(t);
    }

}
