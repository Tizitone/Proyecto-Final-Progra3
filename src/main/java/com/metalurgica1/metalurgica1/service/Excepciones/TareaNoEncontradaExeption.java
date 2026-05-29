package com.metalurgica1.metalurgica1.service.Excepciones;

public class TareaNoEncontradaExeption extends Exception {
    public TareaNoEncontradaExeption(String message) {
        super(message);
    }

    public TareaNoEncontradaExeption(Long id) {
        super("tarea con la id:"+ id+" no encontrada");
    }
}
