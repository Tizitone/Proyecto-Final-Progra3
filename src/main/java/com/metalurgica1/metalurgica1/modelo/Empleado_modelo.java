package com.metalurgica1.metalurgica1.modelo;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_acceso", discriminatorType = DiscriminatorType.STRING)
public abstract class Empleado_modelo extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long legajo;


}
