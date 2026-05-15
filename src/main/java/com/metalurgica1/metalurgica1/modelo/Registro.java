package com.metalurgica1.metalurgica1.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registro {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nombre de la columna en la tabla SQL
    private Cliente cliente;
}
