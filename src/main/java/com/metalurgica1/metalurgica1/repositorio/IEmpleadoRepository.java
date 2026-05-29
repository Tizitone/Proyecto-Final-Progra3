package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
}
