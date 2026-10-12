package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class Articulo extends AuditoriaApp {
    private Rubro rubro;
    private String codigo;
    private String denominacion;
    private Marca marca;
}