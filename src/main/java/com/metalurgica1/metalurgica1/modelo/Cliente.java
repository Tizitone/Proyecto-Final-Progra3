package com.metalurgica1.metalurgica1.modelo;


import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
public class Cliente extends Persona{

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private ArrayList<Registro> encargos = new ArrayList<>();

    public boolean verListaEncargos(){
        return encargos != null && !encargos.isEmpty();
    }

    @PrePersist
    public void asignarEtiqueta(){
        if(this.getEtiquetaDeAcceso() == null){
            this.setEtiquetaDeAcceso(EEtiquetaDeAcceso.CLIENTE);
        }
    }

}
