package com.metalurgica1.metalurgica1.DTO;

import java.util.List;

public record RegistroDTO(
        Long id,
        String titulo,
        Long tareaId,
        Long clienteId,
        List<com.metalurgica1.metalurgica1.modelo.Empleado> participantesId){
}
