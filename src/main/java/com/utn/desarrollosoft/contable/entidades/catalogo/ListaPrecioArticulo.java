package com.utn.desarrollosoft.contable.entidades.catalogo;

import com.utn.desarrollosoft.contable.entidades.base.AuditoriaApp;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lista_precios_articulos")
public class ListaPrecioArticulo extends AuditoriaApp {
    // Una lista de precios puede estar en varias listas de precios de diferentes articulos
    @ManyToOne
    @JoinColumn(name = "lista_precio_id")
    private ListaPrecio listaPrecio;

    private double precioVenta;

    // Un artículo puede estar en varias listas de precios de articulos
    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;
}
