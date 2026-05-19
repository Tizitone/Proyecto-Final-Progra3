package com.metalurgica1.metalurgica1.modelo;


import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administradores")
public class Administrador extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_administrador;

    @PrePersist
    public void asignarEtiqueta(){
        if(this.getEtiquetaDeAcceso()== null){
            this.setEtiquetaDeAcceso(EEtiquetaDeAcceso.ADMIN);
        }
    }
    //nota: administrador no es un empleado, por lo que hereda de persona
}
