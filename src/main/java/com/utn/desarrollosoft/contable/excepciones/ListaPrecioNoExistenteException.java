package com.utn.desarrollosoft.contable.excepciones;

public class ListaPrecioNoExistenteException extends RuntimeException {
    public ListaPrecioNoExistenteException(String message) {
        super(message);
    }
}
