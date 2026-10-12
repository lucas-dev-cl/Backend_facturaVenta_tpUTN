package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import lombok.Data;

@Data
public class ListaPrecioArticulo extends AuditoriaApp {
    private ListaPrecio listaPrecio;
    private double precioVenta;
    private Articulo articulo;
}