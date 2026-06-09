package com.metalurgica1.metalurgica1.modelo;


import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administradores")
public class Administrador extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_administrador;

    @Override
    public EEtiquetaDeAcceso getEtiquetaDeAcceso() {
        return EEtiquetaDeAcceso.ADMIN;
    }

}
