package com.miempresa.exceptions;

// Esta es una Unchecked Exception: El compilador NO obligará a manejarla.
// Usada cuando se intenta crear una entidad con un ID que ya existe.
public class IdDuplicadoException extends RuntimeException {
    public IdDuplicadoException(String message) {
        super(message);
    }
    public IdDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}