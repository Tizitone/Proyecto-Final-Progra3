package com.metalurgica1.metalurgica1.service.Excepciones;

public class EmpleadoNoEncontradoException extends Exception {
    public EmpleadoNoEncontradoException(String message) {
        super(message);
    }

    public EmpleadoNoEncontradoException(Long id) {
        super("Gerente con el id:"+ id+ " no encontrado");
    }
}
