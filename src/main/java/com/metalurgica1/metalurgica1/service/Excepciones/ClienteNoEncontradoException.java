package com.metalurgica1.metalurgica1.service.Excepciones;

public class ClienteNoEncontradoException extends Exception {
    public ClienteNoEncontradoException(String message) {
        super(message);
    }

    public ClienteNoEncontradoException(Long id) {
        super("tarea con la id:"+ id+" no encontrada");
    }
}
