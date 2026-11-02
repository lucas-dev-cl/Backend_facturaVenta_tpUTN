package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;

@Builder
public record CondicionIvaResponse(
        Integer id,
        String denominacion,
        Integer codigoAfip
) {
}
