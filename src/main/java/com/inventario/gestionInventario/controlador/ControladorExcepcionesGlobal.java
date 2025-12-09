package com.inventario.gestionInventario.controlador;

import com.inventario.gestionInventario.excepciones.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // 1. Indica que esta clase manejará excepciones de toda la API
public class ControladorExcepcionesGlobal {

    // 2. Este método intercepta la excepción RecursoNoEncontradoException
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Object> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {

        // 3. Creamos el cuerpo de la respuesta JSON personalizado
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("error", "No encontrado");
        responseBody.put("mensaje_personalizado", ex.getMessage()); // <-- EL MENSAJE CLAVE

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}