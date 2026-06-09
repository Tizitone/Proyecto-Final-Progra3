package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("GERENTE")
public class Empleado_Gerente extends Empleado_modelo{

    @Transient
    private List<Registro> listaRegistros = new ArrayList<>();

    @Transient
    private List<Cliente> listaClientes = new ArrayList<>();

    @Transient
    private List<Tarea> listaTareas = new ArrayList<>();

    public boolean modificarEmpleado(Long dni){
        return this.getDni().equals(dni);
    }

    @Override
    public EEtiquetaDeAcceso getEtiquetaDeAcceso() {
        return EEtiquetaDeAcceso.GERENTE;
    }
}
