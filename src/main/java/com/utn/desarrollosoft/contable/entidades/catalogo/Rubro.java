package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class Rubro extends AuditoriaApp {
    private String denominacion;
    private Integer codigo;
}