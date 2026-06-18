package com.metalurgica1.metalurgica1.service.Excepciones;

public class AdministradorNoEncontradoException extends Exception {
    public AdministradorNoEncontradoException(String message) {
        super(message);
    }

    public AdministradorNoEncontradoException(Long id) {
        super("El administrador com id:"+ id+" no se encontro");
    }
}
