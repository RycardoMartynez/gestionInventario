package com.inventario.gestionInventario.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// CAMBIO CLAVE: Usamos 'reason' para forzar que el mensaje se muestre
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recurso no encontrado")
public class RecursoNoEncontradoException extends RuntimeException {

    // Constructor que acepta el mensaje de error
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
