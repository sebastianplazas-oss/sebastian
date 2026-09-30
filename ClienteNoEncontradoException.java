package com.miempresa. exceptions;

public class ClienteNoEncontradoException extends Exception {
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
    public ClienteNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}