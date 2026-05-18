package com.metalurgica1.metalurgica1.modelo.enums;

public enum ECategorias {
    LIGERO("Ligero"),MEDIANO("Mediano"),PESADO("Pesado");

    private String categoria;

    ECategorias(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
