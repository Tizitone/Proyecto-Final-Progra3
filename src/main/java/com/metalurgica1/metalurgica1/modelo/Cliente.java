package com.metalurgica1.metalurgica1.modelo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
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

    public EEtiquetaDeAcceso getEtiquetaDeAcceso() {
        return EEtiquetaDeAcceso.CLIENTE;
    }

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EEstadoActividad eEstadoActividad;
}
