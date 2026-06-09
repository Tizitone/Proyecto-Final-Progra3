package com.metalurgica1.metalurgica1.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EEstadoActividad eEstadoActividad = EEstadoActividad.ACTIVO;
}
