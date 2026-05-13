package modelo;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Empleado_modelo extends Persona{

    private int legajo;
}
