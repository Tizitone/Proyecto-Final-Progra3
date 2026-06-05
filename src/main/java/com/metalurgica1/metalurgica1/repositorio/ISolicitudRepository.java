package com.metalurgica1.metalurgica1.repositorio;


import com.metalurgica1.metalurgica1.modelo.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISolicitudRepository extends JpaRepository<Solicitud,Long>{


    List<Solicitud> findByDescripcionContainingIgnoreCase(String descripcion);
}
