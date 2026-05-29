package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Empleado_Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGerenteRepository extends JpaRepository<Empleado_Gerente,Long> {
}
