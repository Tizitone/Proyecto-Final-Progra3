package modelo;


import enums.EEtiquetaDeAcceso;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Administrador extends Empleado{

    @PrePersist
    public void asignarEtiqueta(){
        if(this.getEtiquetaDeAcceso()== null){
            this.setEtiquetaDeAcceso(EEtiquetaDeAcceso.ADMIN);
        }
    }
}
