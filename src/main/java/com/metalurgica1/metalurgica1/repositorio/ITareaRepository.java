package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Tarea;
import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ITareaRepository extends JpaRepository<Tarea,Long> {
    List<Tarea> findByDescripcionGeneralContainingIgnoreCaseOrDescripcionMaterialContainingIgnoreCase(
            String descripcionGeneral, String descripcionMaterial);
    List<Tarea> findByCategorias(ECategorias categorias);
    List<Tarea> findByFechaDeEntrega(LocalDate fecha);

}
