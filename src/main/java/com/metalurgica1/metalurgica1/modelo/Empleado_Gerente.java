package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

@Data
@Entity
@DiscriminatorValue("GERENTE")
public class Empleado_Gerente extends Empleado_modelo{

    @Transient
    private List<Registro> listaRegistros;

    @Transient
    private List<Cliente> listaClientes;

    @Transient
    private List<Tarea> listaTareas;

    public boolean modificarEmpleado(Long dni){
        return this.getDni()== dni;
    }

    @PrePersist
    public void asignarEtiqueta(){
        if(this.getEtiquetaDeAcceso()== null){
            this.setEtiquetaDeAcceso(EEtiquetaDeAcceso.GERENTE);
        }
    }

    // nota a las listas les quite el static, porque jpa no dejaba ejecutar
}
