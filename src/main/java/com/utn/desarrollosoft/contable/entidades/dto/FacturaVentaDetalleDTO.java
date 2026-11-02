package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class FacturaVentaDetalleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer productoId;
    private String productoDenominacion;
    private Integer listaPrecioId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public FacturaVentaDetalleDTO() {
    }

    public FacturaVentaDetalleDTO(Long id, Integer productoId, String productoDescripcion, Integer listaPrecioId, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.productoId = productoId;
        this.productoDenominacion = productoDescripcion;
        this.listaPrecioId = listaPrecioId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public String getProductoDenominacion() {
        return productoDenominacion;
    }

    public void setProductoDenominacion(String productoDenominacion) {
        this.productoDenominacion = productoDenominacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getListaPrecioId() {
        return listaPrecioId;
    }

    public void setListaPrecioId(Integer listaPrecioArticuloId) {
        this.listaPrecioId = listaPrecioArticuloId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    private Double calcularSubtotal() {
        if (cantidad == null || precioUnitario == null) {
            return 0.0;
        }
        return cantidad * precioUnitario;
    }
}
