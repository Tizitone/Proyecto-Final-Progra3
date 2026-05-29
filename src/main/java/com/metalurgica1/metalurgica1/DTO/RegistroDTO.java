package com.metalurgica1.metalurgica1.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RegistroDTO {
    private Long id;
    private String titulo;
    private Long tareaId;
    private Long clienteId;
    private List<Long> participantesId;
}
