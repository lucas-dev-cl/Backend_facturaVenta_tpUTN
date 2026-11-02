package com.utn.desarrollosoft.contable.excepciones;

public class ListaPrecioArticuloNoExistenteException extends RuntimeException {
    public ListaPrecioArticuloNoExistenteException(String message) {
        super(message);
    }
}
