package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.dto.CrearTareaDTO;
import com.metalurgica1.metalurgica1.dto.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<TareaDTO> listarTareas(){
        return iTareaRepository
                .findAll()
                .stream()
                .map(t -> new TareaDTO(t.getCategorias(),
                        t.getFechaDeEntrega(),
                        t.getFechaDeRegistro(),
                        t.getDescripcionMaterial(),
                        t.getDescripcionGeneral()))
                .collect(Collectors.toList());
    }
    private Tarea buscarTarea(Long id) throws TareaNoEncontradaExeption {
        Tarea t = iTareaRepository.findById(id)
                .orElseThrow(()-> new TareaNoEncontradaExeption(id));
        return t;
    }

    public TareaDTO buscarPorId(Long id) throws TareaNoEncontradaExeption {
        Tarea t = buscarTarea(id);
        return new TareaDTO(t.getCategorias(),
                t.getFechaDeEntrega(),
                t.getFechaDeRegistro(),
                t.getDescripcionMaterial(),
                t.getDescripcionGeneral());
    }

    public List<Tarea> buscarPorTexto(String texto){
        return iTareaRepository.findByDescripcionGeneralContainingIgnoreCaseOrDescripcionMaterialContainingIgnoreCase(
                texto,
                texto
        );
    }

    public List<Tarea> buscarPorCategoria(ECategorias categorias){
        return iTareaRepository.findByCategorias(categorias);
    }

    public List<Tarea> buscarPorFecha(LocalDate fecha){
        return iTareaRepository.findByFechaDeEntrega(fecha);
    }

    public CrearTareaDTO crearTarea(CrearTareaDTO dto){
        Tarea tarea = new Tarea();
        tarea.setCategorias(dto.categorias());
        tarea.setFechaDeRegistro(LocalDateTime.now());
        tarea.setFechaDeEntrega(dto.fechaDeEntrega());
        tarea.setDescripcionMaterial(dto.descripcionMaterial());
        tarea.setDescripcionGeneral(dto.descripcionGeneral());

        Tarea nuevaTarea = iTareaRepository.save(tarea);

        return new CrearTareaDTO(
                nuevaTarea.getCategorias(),
                nuevaTarea.getFechaDeEntrega(),
                nuevaTarea.getDescripcionMaterial(),
                nuevaTarea.getDescripcionGeneral());
    }

    public CrearTareaDTO modificarTarea(Long id, CrearTareaDTO dto) throws TareaNoEncontradaExeption {
        Tarea tarea = buscarTarea(id);
        tarea.setCategorias(dto.categorias());
        tarea.setFechaDeEntrega(dto.fechaDeEntrega());
        tarea.setDescripcionMaterial(dto.descripcionMaterial());
        tarea.setDescripcionGeneral(dto.descripcionGeneral());

        Tarea nuevaTarea = iTareaRepository.save(tarea);

        return new CrearTareaDTO(
                nuevaTarea.getCategorias(),
                nuevaTarea.getFechaDeEntrega(),
                nuevaTarea.getDescripcionMaterial(),
                nuevaTarea.getDescripcionGeneral());
    }

    public void eliminarTarea(Long id){
        if (!iTareaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada");
        }

        iTareaRepository.deleteById(id);
    }

}
