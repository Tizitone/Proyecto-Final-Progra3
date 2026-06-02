package com.metalurgica1.metalurgica1.DTO;

public record SolicitudDTO(
        Long id,
        String nombre,
        String email,
        String telefono,
        String descripcion
) {
}
