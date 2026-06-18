package com.metalurgica1.metalurgica1.DTO;

import jakarta.validation.constraints.*;

public record CrearAdministradorDTO(
        @NotBlank(message = "El email no puede estar en blanco")
        @Email(message = "No es un email valido")
        String email,

        String contrasenia,

        @NotBlank(message = "El email no puede estar en blanco")
        String nombre,

        @NotBlank(message = "El telefono no puede estar en blanco")
        @Pattern(regexp = "^\\d{7,11}$", message = "El telefono debe contener entre 7 y 11 digitos numericos")
        String telefono,

        @NotNull(message = "El DNI no puede ser nulo")
        @Positive(message = "El DNI tiene que ser positivo")
        @Max(value = 99999999, message = "DNI fuera de rango")
        @Min(value = 1000000, message = "DNI fuera de rango")
        Long dni
) {
}
