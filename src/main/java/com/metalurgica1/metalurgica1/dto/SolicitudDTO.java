package com.metalurgica1.metalurgica1.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SolicitudDTO(
        Long id,
        @NotNull(message = "El nombre no puede ser nulo")
        @NotBlank(message = "El nombre no puede estar en blanco")
        String nombre,
        String email,
        @NotNull(message = "El telefono no puede ser nulo")
        @Pattern(regexp = "^\\d{7,11}$", message = "El telefono debe contener entre 7 y 11 digitos numericos")
        String telefono,
        @NotNull(message = "La descripcion no puede ser nula")
        @NotBlank(message = "La descripcion no puede estar en blanco")
        String descripcion
) {
}
