package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRequestDTO {
    private Long numero;
    private Date fechaEmision;

    // IDs para vincular las relaciones solicitadas
    private Long clienteId;
    private Long condicionIvaId;
    private Long puntoVentaId;
    private Long tipoMonedaId;

    // Lista de detalles de la factura
    private List<FacturaDetalleRequestDTO> detalles = new ArrayList<>();
}