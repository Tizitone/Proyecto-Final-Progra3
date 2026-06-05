package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRegistroRepository extends JpaRepository<Registro,Long> {
    List<Registro> findByTitulo(String titulo);
    List<Registro> findByPublicadoTrue();
}
