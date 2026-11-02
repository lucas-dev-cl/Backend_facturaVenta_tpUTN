package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;

@Builder
public record PuntoVentaResponse(
        Integer id,
        Integer numero,
        String descripcion,
        String tipoEmision,
        String domicilioComercial
) {
}
