package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;

@Builder
public record ListaPrecioResponse(
        Integer id,
        String denominacion
) {
}
