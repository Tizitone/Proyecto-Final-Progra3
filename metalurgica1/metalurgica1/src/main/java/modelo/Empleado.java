package modelo;

import enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
public class Empleado extends Empleado_modelo {

    @ManyToMany
    @JoinTable(
            name = "empleado_historial",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "tarea_id")
    )
    private ArrayList<Tarea> historial = new ArrayList<>();

    public boolean modificarEmpleado(int dni){
        return this.getId()==dni;
    }

    @PrePersist
    public void asignarEtiqueta(){
        if(this.getEtiquetaDeAcceso()== null){
            this.setEtiquetaDeAcceso(EEtiquetaDeAcceso.EMPLEADO);
        }
    }

}
