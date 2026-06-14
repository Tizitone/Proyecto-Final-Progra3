package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EProceso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "e_proceso")
    private EProceso eProceso;

    @ManyToMany
    @JoinTable(
            name = "registro_empleados",
            joinColumns =  @JoinColumn(name =  "registro_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> participantes = new ArrayList<>();

    @Column(nullable = false)
    private Boolean publicado = false;
}
