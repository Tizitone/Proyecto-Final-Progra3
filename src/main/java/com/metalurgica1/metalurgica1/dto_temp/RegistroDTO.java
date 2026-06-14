package com.metalurgica1.metalurgica1.dto_temp;

import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.enums.EProceso;

import java.util.List;
// no hace falta validar ya que este dto solo muestra informacion
public record RegistroDTO(
        Long id,
        String titulo,
        Long tareaId,
        Long clienteId,
        EProceso proceso,
        List<Empleado> participantesId,
        Boolean publicado){
}
