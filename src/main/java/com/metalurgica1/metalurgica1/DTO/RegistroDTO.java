package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import java.util.List;

public record RegistroDTO(
        Long id,
        String titulo,
        Long tareaId,
        Long clienteId,
        List<Empleado> participantesId,
        Boolean publicado){
}
