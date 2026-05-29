package com.metalurgica1.metalurgica1.DTO;

import lombok.Data;

import java.util.List;

public record RegistroDTO(
        Long id,
        String titulo,
        Long tareaId,
        Long clienteId,
        List<Long> participantesId){
}
