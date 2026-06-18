package com.metalurgica1.metalurgica1.modelo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "etiqueta_de_acceso", discriminatorType = DiscriminatorType.STRING)
public abstract class Empleado_modelo extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long legajo;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EEstadoActividad eEstadoActividad;
}
