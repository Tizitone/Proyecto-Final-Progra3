package com.metalurgica1.metalurgica1.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_Empleado", discriminatorType = DiscriminatorType.STRING)
public abstract class Empleado_modelo extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long legajo;
}
//nota: estaba pensando que empleado modelo sea la tabla principal, y que ahi se junten todos los empleados
//      ya que empleado y empleado_gerente, solo se diferencian por las listas que tienen, y ademas,
//      las listas de empleado_gerente son estaticas, por lo que, no haria falta guardarlas, solo consultar
//      a las tablas para leerlas.