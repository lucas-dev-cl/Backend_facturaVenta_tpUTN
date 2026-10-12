package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class Marca extends AuditoriaApp {
    private String denominacion;
    private Integer codigo;
}