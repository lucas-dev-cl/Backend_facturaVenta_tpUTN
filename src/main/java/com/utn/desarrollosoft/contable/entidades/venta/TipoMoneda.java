package com.utn.desarrollosoft.contable.entidades.venta;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class TipoMoneda extends AuditoriaApp {
    private String codigoAfip;
    private String denominacion;
    private String simbolo;
}