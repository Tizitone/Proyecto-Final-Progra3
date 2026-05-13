package modelo;

import enums.EEtiquetaDeAcceso;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Empleado_Gerente extends Empleado{

    @Transient
    private static List<Registro> listaRegistros;

    @Transient
    private static List<Cliente> listaClientes;

    @Transient
    private static List<Tarea> listaTareas;

    public void modificarEmpleado(int dni){
        return this.getDni()== dni;
    }

    @PrePersist
    public void asignarEtiqueta(){
        if(this.getEtiquetaDeAcceso()== null){
            this.setEtiquetaDeAcceso(EEtiquetaDeAcceso.GERENTE);
        }
    }
}
