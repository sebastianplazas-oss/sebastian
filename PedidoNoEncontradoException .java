package com.miempresa.exceptions;

// Esta es una Checked Exception: El compilador obligará a manejarla o declararla.
public class PedidoNoEncontradoException extends Exception {
    public PedidoNoEncontradoException(String message) {
        super(message);
    }
    public PedidoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}