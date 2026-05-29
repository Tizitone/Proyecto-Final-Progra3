package com.metalurgica1.metalurgica1.servicios.Excepciones;

public class AdministradorNoEncontradoException extends Exception {
    public AdministradorNoEncontradoException(String message) {
        super(message);
    }

    public AdministradorNoEncontradoException() {
        super("El administrador no se encontro");
    }
}
