package com.miempresa.exceptions;

// Esta es una Unchecked Exception: El compilador NO obligara a manejarla.
// Usada para errores de validacion de entrada, que son "fallos del programador/usuario".
public class DatosInvalidosException extends RuntimeException {
    public DatosInvalidosException(String message) {
        super(message);
    }
    public DatosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }
}