package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CrearTareaDTO(
        @NotNull(message = "La tarea tiene que tener una categoria")
        ECategorias categorias,
        @FutureOrPresent(message = "La fecha de entrega tiene que ser en un tiempo proximo")
        LocalDate fechaDeEntrega,
        String descripcionMaterial,
        @NotBlank(message = "La descripcion general no puede quedar en blanco")
        String descripcionGeneral
) {
}
