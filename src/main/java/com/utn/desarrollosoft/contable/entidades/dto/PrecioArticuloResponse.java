package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;

@Builder
public record PrecioArticuloResponse(
        String listaPrecioDenominacion,
        Double precioVenta
) {
}
