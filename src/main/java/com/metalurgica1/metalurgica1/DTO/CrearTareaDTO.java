package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.ECategorias;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;

public record CrearTareaDTO(
        @NotNull(message = "La tarea tiene que tener un titulo")
        String titulo,
        @NotNull(message = "La tarea tiene que tener una categoria")
        ECategorias categorias,
        @FutureOrPresent(message = "La fecha de entrega tiene que ser en un tiempo proximo")
        LocalDate fechaDeEntrega,
        String descripcionMaterial,
        @NotBlank(message = "La descripcion general no puede quedar en blanco")
        String descripcionGeneral,
        @Positive(message = "La cantidad no puede ser cero o negativa")
        int cant

) {
}
