package com.utn.desarrollosoft.contable.excepciones;

public class ProductoNoExistenteException extends RuntimeException {
    public ProductoNoExistenteException(String message) {
        super(message);
    }
}
