package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Persona {

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
// nota: persona no tiene un id, los id se definen en las clases que se derivan de esta.
}
