package com.metalurgica1.metalurgica1.modelo;


import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Registro> encargos = new ArrayList<>();

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
