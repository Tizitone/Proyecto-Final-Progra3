package com.metalurgica1.metalurgica1.DTO;

public record CrearClienteDTO(
        String email,
        String contrasenia,
        String nombre,
        String telefono,
        Long dni
) {
}
