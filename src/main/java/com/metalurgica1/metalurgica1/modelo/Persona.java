package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String contrasenia;
    private String nombre;
    private String telefono;
    private int dni;

    @Enumerated(EnumType.STRING)
    private EEtiquetaDeAcceso etiquetaDeAcceso;

    public boolean validarCuenta(String correo, String contrasenia) {
        return this.email.equals(correo) && this.contrasenia.equals(contrasenia);
    }

}
