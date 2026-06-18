package com.metalurgica1.metalurgica1.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ECategorias categorias;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaDeRegistro = LocalDateTime.now();
    @JsonProperty("fecha_de_entrega")
    private LocalDate fechaDeEntrega ;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("descripcion_material")
    private String descripcionMaterial;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("descripcion_general")
    private String descripcionGeneral;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EEstadoActividad eEstadoActividad;
}
