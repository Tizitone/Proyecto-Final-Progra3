package com.metalurgica1.metalurgica1.DTO;

public record ClienteDTO(
        String email,
        String nombre,
        String telefono,
        Long dni,
        Long idCliente) {
}
