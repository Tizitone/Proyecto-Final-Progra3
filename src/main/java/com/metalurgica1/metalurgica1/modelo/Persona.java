package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Persona {

    @NotNull(message = "el email no puede estar vacio")
    @Email(message = "Tiene que ingresar un email valido")
    private String email;

    @NotNull(message = "la contraseña no puede estar vacia")
    private String contrasenia;

    @NotNull(message = "Tiene que tener un nombre")
    private String nombre;

    @NotNull(message = "Debe tener un telefono")
    private String telefono;
    @Max(value = 99999999, message = "dni fuera de rango")
    private Long dni;

    public abstract EEtiquetaDeAcceso getEtiquetaDeAcceso();

    public boolean validarCuenta(String correo, String contrasenia) {
        return this.email.equals(correo) && this.contrasenia.equals(contrasenia);
    }
}
