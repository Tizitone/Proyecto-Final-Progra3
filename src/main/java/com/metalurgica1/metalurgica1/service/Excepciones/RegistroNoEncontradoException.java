package com.metalurgica1.metalurgica1.service.Excepciones;

public class RegistroNoEncontradoException extends Exception {
    public RegistroNoEncontradoException(String message) {
        super(message);
    }

    public RegistroNoEncontradoException(Long id) {
        super("tarea con la id:"+ id+" no encontrada");
    }
}
