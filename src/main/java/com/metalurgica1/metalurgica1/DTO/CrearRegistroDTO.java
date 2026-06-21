package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.enums.EProceso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CrearRegistroDTO(

        @NotBlank(message = "El titulo no puede estar en blanco")
        String titulo,

        @Positive(message = "El nro de la tarea debe ser positivo")
        Long tareaId,

        @Positive(message = "El nro del cliente debe ser positivo")
        Long clienteId,

        @NotNull
        EProceso proceso,

        @NotEmpty(message = "La lista de empleados no puede estar vacia")
        List<Long> participantesId,

        Boolean publicado

) {
}
