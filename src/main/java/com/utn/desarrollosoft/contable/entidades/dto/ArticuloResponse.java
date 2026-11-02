package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record ArticuloResponse(
    Long id,
    String codigo,
    String denominacion,
    MarcaResponse marcaResponse,
    RubroResponse rubroResponse,
    List<PrecioArticuloResponse> precios
) {
}
