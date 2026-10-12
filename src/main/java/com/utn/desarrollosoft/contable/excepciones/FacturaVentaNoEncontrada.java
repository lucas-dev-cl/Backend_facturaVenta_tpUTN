package com.utn.desarrollosoft.contable.excepciones;

public class FacturaVentaNoEncontrada extends RuntimeException {
    public FacturaVentaNoEncontrada(String message) {
        super(message);
    }
}
