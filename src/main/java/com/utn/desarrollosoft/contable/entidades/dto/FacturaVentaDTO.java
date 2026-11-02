package com.utn.desarrollosoft.contable.entidades.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class FacturaVentaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long numeroFactura;
    private LocalDateTime fechaEmision;
    private Integer clienteId;
    private String clienteDenominacion;
    private Integer condicionIvaId;
    private String condicionIvaDenominacion;
    private Integer puntoVentaId;
    private String puntoVentaDescripcion;
    private Double importeTotal;
    private List<FacturaVentaDetalleDTO> detalles;

    public FacturaVentaDTO() {
        this.detalles = new ArrayList<>();
    }

    public Integer getCantidadItems() {
        return detalles != null ? detalles.size() : 0;
    }
}
