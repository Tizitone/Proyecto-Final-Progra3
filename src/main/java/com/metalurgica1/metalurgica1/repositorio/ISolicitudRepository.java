package com.metalurgica1.metalurgica1.repositorio;


import com.metalurgica1.metalurgica1.modelo.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISolicitudRepository extends JpaRepository<Solicitud,Long>{

    @Query(value = "SELECT * FROM solicitudes WHERE descripcion LIKE CONCAT('%', :palabras, '%')", nativeQuery = true)
    List<Solicitud> findByDescripcionPorPalabras(@Param("palabras") String palabras);
}
