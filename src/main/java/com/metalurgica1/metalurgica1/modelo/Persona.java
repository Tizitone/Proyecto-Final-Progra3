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
    private Long dni;

    public abstract EEtiquetaDeAcceso getEtiquetaDeAcceso();

    public boolean validarCuenta(String correo, String contrasenia) {
        return this.email.equals(correo) && this.contrasenia.equals(contrasenia);
    }


}
