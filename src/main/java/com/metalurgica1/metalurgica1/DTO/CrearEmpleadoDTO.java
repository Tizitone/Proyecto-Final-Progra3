package com.metalurgica1.metalurgica1.dto;

public record CrearEmpleadoDTO(
        String email,
        String contrasenia,
        String nombre,
        String telefono,
        Long dni
) {
}
