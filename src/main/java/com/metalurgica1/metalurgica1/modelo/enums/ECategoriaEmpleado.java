package com.metalurgica1.metalurgica1.modelo.enums;

public enum ECategoriaEmpleado {
    SIN_CATEGORIA("Sin categoria"),TORNERO("Tornero"),SOLDADOR("Soldador"),CORTADOR("Cortador"),AYUDANTE("Ayudante");

    private String tipo;

    ECategoriaEmpleado(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
