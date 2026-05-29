package com.metalurgica1.metalurgica1.dto;

import lombok.Data;

import java.util.List;

public record RegistroDTO(
        String titulo,
        Long tareaId,
        Long clienteId,
        List<Long> participantesId){
}
