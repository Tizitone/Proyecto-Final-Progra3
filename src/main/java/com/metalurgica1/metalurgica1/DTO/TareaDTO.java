package com.metalurgica1.metalurgica1.dto;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record TareaDTO(
     ECategorias categorias,
     LocalDate fechaDeEntrega,
     LocalDateTime fechaDeRegistro,
     String descripcionMaterial,
     String descripcionGeneral){
}
