package com.metalurgica1.metalurgica1.modelo;


import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
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
