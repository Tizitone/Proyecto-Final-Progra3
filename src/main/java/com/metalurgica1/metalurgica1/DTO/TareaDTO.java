package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import java.time.LocalDate;
import java.time.LocalDateTime;

// no hace falta validar ya que este dto solo muestra informacion
public record TareaDTO(
     Long idTarea,
     ECategorias categorias,
     LocalDate fechaDeEntrega,
     LocalDateTime fechaDeRegistro,
     String descripcionMaterial,
     String descripcionGeneral
) {
}
