package com.utn.desarrollosoft.contable.entidades.venta;

import com.utn.desarrollosoft.contable.entidades.catalogo.ListaPrecioArticulo;
import com.utn.desarrollosoft.contable.entidades.base.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "factura_ventas_detalles")
public class FacturaVentaDetalle extends EntityId {
    @ManyToOne
    @JoinColumn(name = "factura_venta_id", nullable = false)
    private FacturaVenta factura;

    @ManyToOne
    @JoinColumn(name = "lista_precio_articulo_id", nullable = false)
    private ListaPrecioArticulo listaPrecioArticulo;

    private String descripcion;
    private double cantidad;
    private double precioUnitario;
    private double porcentajeBonificacion;
    private double importeNeto;
    private double importeIva;
    private double importeSubtotal;
}