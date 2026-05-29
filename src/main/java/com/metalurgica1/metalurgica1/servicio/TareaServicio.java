package com.metalurgica1.metalurgica1.servicio;

import com.metalurgica1.metalurgica1.DTO.TareaDTO;
import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.repositorio.ITareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TareaServicio {
    private final ITareaRepository iTareaRepository;


    public TareaServicio(ITareaRepository iTareaRepository) {
        this.iTareaRepository = iTareaRepository;
    }

    public List<Tarea> listarTareas(){
        return iTareaRepository.findAll();
    }

    public Tarea buscarPorId(Long id){
        return iTareaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada"));
    }

    public Tarea crearTarea(TareaDTO dto){
        Tarea tarea = new Tarea();
        tarea.setCategorias(dto.getCategorias());
        tarea.setFechaDeRegistro(LocalDateTime.now());
        tarea.setFechaDeEntrega(dto.getFechaDeEntrega());
        tarea.setDescripcionMaterial(dto.getDescripcionMaterial());
        tarea.setDescripcionGeneral(dto.getDescripcionGeneral());
        return iTareaRepository.save(tarea);
    }

    public Tarea modificarTarea(Long id, TareaDTO dto){
        Tarea tarea = buscarPorId(id);
        tarea.setCategorias(dto.getCategorias());
        tarea.setFechaDeEntrega(dto.getFechaDeEntrega());
        tarea.setDescripcionMaterial(dto.getDescripcionMaterial());
        tarea.setDescripcionGeneral(dto.getDescripcionGeneral());
        return iTareaRepository.save(tarea);
    }

    public void eliminarTarea(Long id){
        if (!iTareaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada");
        }

        iTareaRepository.deleteById(id);
    }

}
