package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    Empleado findByEmail (String email);
    List<Empleado> findByNombre (String nombre);
    Empleado findByTelefono (String telefono);
    Empleado findByDni (Long dni);
    Empleado findByLegajo (Long legajo);
}
