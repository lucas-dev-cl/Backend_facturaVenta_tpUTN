package com.utn.desarrollosoft.contable.entidades.cliente;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class CondicionIva extends AuditoriaApp {
    private int codigoAfip;
    private String denominacion;
}