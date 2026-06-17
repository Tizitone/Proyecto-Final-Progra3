package com.metalurgica1.metalurgica1.modelo;

import com.metalurgica1.metalurgica1.modelo.enums.EEtiquetaDeAcceso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("EMPLEADO")
public class Empleado extends Empleado_modelo {

    @ManyToMany
    @JoinTable(
            name = "empleado_historial",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "tarea_id")
    )
    private List<Tarea> historial = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "etiqueta_de_acceso", insertable = false, updatable = false)
    private EEtiquetaDeAcceso etiquetaDeAcceso = EEtiquetaDeAcceso.EMPLEADO;

    @Override
    public EEtiquetaDeAcceso getEtiquetaDeAcceso() {
        return EEtiquetaDeAcceso.EMPLEADO;
    }
}
