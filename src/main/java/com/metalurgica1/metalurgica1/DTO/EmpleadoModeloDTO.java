package com.metalurgica1.metalurgica1.DTO;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;

//aca no hace faltan validaciones porque solo muestran los datos
public record EmpleadoModeloDTO(
        Long legajo,
        String email,
        String nombre,
        String telefono,
        Long dni,
        EEtiquetaDeAcceso eEtiquetaDeAcceso
) {
}
