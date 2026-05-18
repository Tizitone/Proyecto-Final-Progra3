package com.metalurgica1.metalurgica1.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;


    @ManyToOne
    @JoinColumn(name = "tarea_id") // Nombre de la columna en la tabla SQL
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nombre de la columna en la tabla SQL
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "registro_empleados",
            joinColumns =  @JoinColumn(name =  "registro_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> participantes = new ArrayList<>();
}
