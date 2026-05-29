package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TareaDTO {
    private Long id;
    private ECategorias categorias;
    private LocalDate fechaDeEntrega;
    private String descripcionMaterial;
    private String descripcionGeneral;
}
