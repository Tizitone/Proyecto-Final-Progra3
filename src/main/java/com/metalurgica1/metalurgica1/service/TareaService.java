package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.CrearTareaDTO;
import com.metalurgica1.metalurgica1.DTO.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;
import jakarta.transaction.Transactional;
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

    private TareaDTO convertirADTO(Tarea t){
        return new TareaDTO(t.getCategorias(),t.getFechaDeEntrega(),
                t.getFechaDeRegistro(),t.getDescripcionMaterial(),
                t.getDescripcionGeneral());
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
        return iTareaRepository.findById(id)
                .orElseThrow(()-> new TareaNoEncontradaExeption(id));
    }

    public TareaDTO buscarPorId(Long id) throws TareaNoEncontradaExeption {
        Tarea t = buscarTarea(id);
        return new TareaDTO(t.getCategorias(),
                t.getFechaDeEntrega(),
                t.getFechaDeRegistro(),
                t.getDescripcionMaterial(),
                t.getDescripcionGeneral());
    }

    public List<TareaDTO> buscarPorTexto(String texto){
        return iTareaRepository.findByDescripcionGeneralContainingIgnoreCaseOrDescripcionMaterialContainingIgnoreCase(
                texto,
                texto)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<TareaDTO> buscarPorCategoria(ECategorias categorias){
        return iTareaRepository.findByCategorias(categorias)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<TareaDTO> buscarPorFecha(LocalDate fecha){
        return iTareaRepository.findByFechaDeEntrega(fecha)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
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
