package com.utn.desarrollosoft.contable.entidades.cliente;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class Cliente extends AuditoriaApp {
    private String cuitCuil;
    private String denominacion;
    private Contacto contacto;
    private Domicilio domicilio;
}