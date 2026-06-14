package com.metalurgica1.metalurgica1.service.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//clase para capturar los errores de validacion
@RestControllerAdvice
public class GlobalExceptionHandler {

    //metodo responseEntity que se llamara automaticamente en el controler si algun atributo con etiqueta de validation tiene algun campo mal.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeValidacion(MethodArgumentNotValidException ex)
    {
        Map<String, String> errores = new HashMap<>(); // se necesita un map para asignar un mensaje a un error
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo,mensajeError);
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST); //devuelve la lista de errores y lanza un badRequest
    }
}
