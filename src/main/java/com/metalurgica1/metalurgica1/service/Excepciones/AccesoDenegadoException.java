package com.metalurgica1.metalurgica1.service.Excepciones;

public class AccesoDenegadoException extends Exception {
    public AccesoDenegadoException(String message) {
        super(message);
    }

    public AccesoDenegadoException() { super("No se permitio el acceso");}
}
