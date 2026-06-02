package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CrearTareaDTO(ECategorias categorias,
                            LocalDate fechaDeEntrega,
                            String descripcionMaterial,
                            String descripcionGeneral) {
}
