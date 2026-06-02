package com.metalurgica1.metalurgica1.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitudes" ,indexes=
        {
                @Index(name = "index_descripcion" ,columnList = "descripcion USING FULL_TEXT")
        })
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
