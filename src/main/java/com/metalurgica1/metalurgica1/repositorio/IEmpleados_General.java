package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Empleado_modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleados_General extends JpaRepository<Empleado_modelo,Long> {
}
