package com.metalurgica1.metalurgica1.service.Excepciones;

public class SolicitudNoEncontradaException extends Exception {
    public SolicitudNoEncontradaException(String message) {

        super("no se encontro la solicitud, "+message);
    }
}
