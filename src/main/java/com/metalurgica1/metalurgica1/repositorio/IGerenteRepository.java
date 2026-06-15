package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Empleado_Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGerenteRepository extends JpaRepository<Empleado_Gerente,Long> {
    Empleado_Gerente findByEmail (String email);
    java.util.Optional<Empleado_Gerente> findByEmailOptional(String email);
    List<Empleado_Gerente> findByNombre (String nombre);
    Empleado_Gerente findByTelefono (String telefono);
    Empleado_Gerente findByDni (Long dni);
    Empleado_Gerente findByLegajo (Long legajo);
}
