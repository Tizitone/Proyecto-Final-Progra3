package com.metalurgica1.metalurgica1.DTO;

// no hace falta validar ya que este dto solo muestra informacion
public record GerenteDTO(

        String email,
        String nombre,
        String telefono,
        Long dni,
        Long legajo
) {

}
