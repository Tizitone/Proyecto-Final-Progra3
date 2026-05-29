package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record TareaDTO(
     Long id,
     ECategorias categorias,
     LocalDate fechaDeEntrega,
     String descripcionMaterial,
     String descripcionGeneral){
}
